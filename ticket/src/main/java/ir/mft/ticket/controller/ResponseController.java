package ir.mft.ticket.controller;


import ir.mft.ticket.enums.Status;
import ir.mft.ticket.exceptions.NoContentException;
import ir.mft.ticket.model.Response;
import ir.mft.ticket.service.ResponseServiceImp;
import ir.mft.ticket.service.TicketServiceImp;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(value = "/response")
public class ResponseController {
    private ResponseServiceImp responseServiceImp;
    private TicketServiceImp ticketServiceImp;

    public ResponseController(ResponseServiceImp responseServiceImp, TicketServiceImp ticketServiceImp) {
        this.responseServiceImp = responseServiceImp;
        this.ticketServiceImp = ticketServiceImp;
    }

    @GetMapping
    public String showResponses(Model model) {
        log.info("Controller-Response-Get-FindAll");
        model.addAttribute("response", new Response());
        model.addAttribute("responseList", responseServiceImp.findAll());
        model.addAttribute("ticketGroupList", ticketServiceImp.findAllTitle());
        return "response";
    }



    @PostMapping
    @ResponseBody
    public Response saveResponse(Response response , BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            log.error("Controller-Response-Post-Save-Error: " + response.toString());
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        log.info("Controller-Response-Post-Save");
        ticketServiceImp.editStatusById(response.getTicket().getId(), Status.answered);
        return responseServiceImp.save(response);
    }

    @PutMapping
    @ResponseBody
    public Response editResponse(Response response , BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            log.error("Controller-Response-Put-Edit-Error: " + response.toString());
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        log.info("Controller-Response-Post-Edit");
        return responseServiceImp.edit(response);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public Response deleteResponse(@PathVariable Long id) throws NoContentException {
        log.info("Controller-Response-Delete-Delete");
        return responseServiceImp.logicalRemove(id);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Response showResponse(@PathVariable Long id) throws NoContentException {
        log.info("Controller-Response-Get-FindById");
        return responseServiceImp.findById(id);
    }


    @GetMapping(value = "/date/{timeStamp}")
    @ResponseBody
    public List<Response> showResponsesByApplicant( @PathVariable("timeStamp") LocalDateTime responseDate) {
        log.info("Controller-Response-Get-FindByDate");
        List<Response> responseList = responseServiceImp.findByDate(responseDate);
        return responseList;
    }

    @GetMapping(value = "/group/{ticketGroup}")
    @ResponseBody
    public List<Response> showResponseByTicketGroup( @PathVariable("ticketGroup") String ticketGroup) {
        log.info("Controller-Response-Get-FindByDate");
        List<Response> responseList = responseServiceImp.findByTicketGroup(ticketGroup);
        return responseList;
    }

}
