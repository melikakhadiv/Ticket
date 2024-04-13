package ir.mft.ticket.controller;


import ir.mft.ticket.model.Response;
import ir.mft.ticket.service.ResponseServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value ="/response")
public class ResponseController {
    private ResponseServiceImp responseServiceImp;

    public ResponseController(ResponseServiceImp responseServiceImp) {
        this.responseServiceImp = responseServiceImp;
    }

    @GetMapping
    public String showResponses(Model model) {
        log.info("Controller-Response-Get-FindAll");
        model.addAttribute("response", new Response());
        model.addAttribute("responseList", responseServiceImp.findAll());
        return "responseForm";
    }

    @GetMapping(value ="/id/{id}")
    public String showResponse(@ModelAttribute("id") Long id){
        log.info("Controller-Response-Get-FindById");
        Response response = responseServiceImp.findById(id);
        if (response != null){
            return "responseForm";
        }else {
            return "error-404";
        }
    }

    @GetMapping(value ="/responder")
    public String showResponsesByApplicant(Model model , @ModelAttribute("responder") String responder) {
        log.info("Controller-Response-Get-FindByResponder");
        List<Response> responseList = responseServiceImp.findByResponder(responder);
        if (!responseList.isEmpty()){
            model.addAttribute("responseList", responseList);
            return "responseForm";
        }else {
            return "error-404";
        }
    }

    @GetMapping(value ="/date")
    public String showResponsesByApplicant(Model model , @ModelAttribute("timeStamp") LocalDate responseDate) {
        log.info("Controller-Response-Get-FindByDate");
        List<Response> responseList = responseServiceImp.findByDate(responseDate);
        if (!responseList.isEmpty()){
            model.addAttribute("responseList", responseList);
            return "responseForm";
        }else {
            return "error-404";
        }
    }

    @PostMapping(value = "/save")
    public String saveResponse(Response response) {
        log.info("Controller-Response-Post-Save: " + response.toString());
        log.info("Controller-Response-Post-Save");
        responseServiceImp.save(response);
        return "redirect:/response";
    }

    @PostMapping(value ="/edit")
    public String editResponse(Response response) {
        log.info("Controller-Response-Post-Edit");
        responseServiceImp.edit(response);
        return "responseForm";
    }

    @PostMapping(value ="/delete")
    public String deleteResponse(Response response) {
        log.info("Controller-Response-Post-Delete");
        responseServiceImp.logicalRemove(response.getId());
        return "redirect:/response";
    }


}
