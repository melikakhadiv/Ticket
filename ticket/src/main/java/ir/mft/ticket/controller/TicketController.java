package ir.mft.ticket.controller;

import ir.mft.ticket.model.Ticket;
import ir.mft.ticket.service.TicketServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value ="/ticket")
public class TicketController {
    private TicketServiceImp ticketServiceImp;

    public TicketController(TicketServiceImp ticketServiceImp) {
        this.ticketServiceImp = ticketServiceImp;
    }

    @GetMapping
    public String showTickets(Model model) {
        log.info("Controller-Ticket-Get-FindAll");
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("ticketList", ticketServiceImp.findAll());
        return "ticketForm";
    }

    @GetMapping(value ="/id/{id}")
    @ResponseBody
    public Ticket showTicketById(@PathVariable("id") Long id , Model model){
        log.info("Controller-Ticket-Get-FindById");
        Ticket ticket = ticketServiceImp.findById(id);
        if (ticket != null){
            log.info(ticket.toString());
//            model.addAttribute("ticketEdit", ticket);
            log.info(ticket.toString());
            return ticket;
        }else {
            return null;
        }
    }

//    @GetMapping(value ="/id")
//    public String showTicket( Long id , Model model){
//        log.info("Controller-Ticket-Get-FindById");
//        Ticket ticket = ticketServiceImp.findById(id);
//        if (ticket != null){
//            log.info(ticket.toString());
//            model.addAttribute("ticketEdit", ticket);
//            log.info(ticket.toString());
//            return "redirect:/ticket";
//        }else {
//            return "ticketForm";
//        }
//    }

    @GetMapping(value ="/applicant")
    public String showTicketsByApplicant(Model model , @ModelAttribute("applicant") String applicant) {
        log.info("Controller-Ticket-Get-FindByApplicant");
        List<Ticket> ticketList = ticketServiceImp.findByApplicant(applicant);
        if (!ticketList.isEmpty()){
            model.addAttribute("ticketList", ticketList);
            return "ticketForm";
        }else {
            return "error-404";
        }
    }

    @GetMapping(value ="/date")
    public String showTicketsByDate(Model model , @ModelAttribute("date") LocalDate date) {
        log.info("Controller-Ticket-Get-FindByDate");
        List<Ticket> ticketList = ticketServiceImp.findByDate(date);
        if (!ticketList.isEmpty()){
            model.addAttribute("ticketList", ticketList);
            return "ticketForm";
        }else {
            return "error-404";
        }
    }

    @PostMapping(value = "/save")
    public String saveTicket(Ticket ticket) {
        log.info("Controller-Ticket-Post-Save: " + ticket.toString());
        log.info("Controller-Ticket-Post-Save");
        ticketServiceImp.save(ticket);
        return "redirect:/ticket";
    }

    @PostMapping(value ="/edit")
    public String editTicket(Ticket ticket) {
        log.info("Controller-Ticket-Post-Edit");
        log.info("Controller-Ticket-Post-Edit" + ticket.toString());
        ticketServiceImp.edit(ticket);
        return "redirect:/ticket";
    }

    @PostMapping(value ="/delete")
    public String deleteTicket(Long id) {
        log.info("Controller-Ticket-Post-Delete: " + id);
        ticketServiceImp.logicalRemove(id);
        return "redirect:/ticket";
    }


}
