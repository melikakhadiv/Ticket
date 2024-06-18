package ir.mft.ticket.controller;


import ir.mft.ticket.enums.Status;
import ir.mft.ticket.exceptions.NoContentException;
import ir.mft.ticket.model.Ticket;
import ir.mft.ticket.service.TicketGroupServiceImp;
import ir.mft.ticket.service.TicketServiceImp;
import jakarta.validation.Valid;
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
@RequestMapping(value = "/ticket")
public class TicketController {
    private TicketServiceImp ticketServiceImp;
    private TicketGroupServiceImp ticketGroupServiceImp;


    public TicketController(TicketServiceImp ticketServiceImp, TicketGroupServiceImp ticketGroupServiceImp) {
        this.ticketServiceImp = ticketServiceImp;
        this.ticketGroupServiceImp = ticketGroupServiceImp;

    }

    @GetMapping
    public String showTickets(Model model) {
        log.info("Controller-Ticket-Get-FindAll");
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("ticketList", ticketServiceImp.findAll());
        model.addAttribute("ticketGroupParents", ticketGroupServiceImp.findByParentRoot());
        return "ticket" ;
    }

    @PostMapping()
    @ResponseBody
    public Ticket saveTicket(@Valid Ticket ticket, BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            log.error("Controller-Ticket-Post-Save-Error: " + ticket.toString());
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        log.info("Controller-Ticket-Post-Save: " + ticket.toString());
        return ticketServiceImp.save(ticket);
    }

    @PutMapping()
    @ResponseBody
    public Ticket editTicket(@Valid Ticket ticket, BindingResult bindingResult) throws NoContentException {
        if (bindingResult.hasErrors()) {
            log.error("Controller-Ticket-Put-Edit-Error: " + ticket.toString());
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        return ticketServiceImp.edit(ticket);

    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public Ticket deleteTicket(@PathVariable Long id, Model model) throws NoContentException {
        log.info("Controller-Ticket-Delete-Delete: " + id);
        return ticketServiceImp.logicalRemove(id);
    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public Ticket showTicket(@PathVariable("id") Long id) throws NoContentException {
        log.info("Controller-Ticket-Get-FindById: " + id);
        return ticketServiceImp.findById(id);
    }



    @GetMapping(value = "/date/{date}")
    @ResponseBody
    public List<Ticket> showTicketsByTimeStamp( @PathVariable("date") LocalDateTime timeStamp) throws NoContentException {
        log.info("Controller-Ticket-Get-FindByDate");
        List<Ticket> ticketList = ticketServiceImp.findByDate(timeStamp);
        return ((ticketList.isEmpty()) ? null : ticketList);
    }

    @GetMapping(value = "/title/{title}")
    @ResponseBody
    public List<Ticket> showTicketsByTitle( @PathVariable("title") String  title) throws NoContentException {
        log.info("Controller-Ticket-Get-FindByTitle");
        List<Ticket> ticketList = ticketServiceImp.findByTitle(title);
        System.out.println("TicketList");
        System.out.println(ticketList);
        return ((ticketList.isEmpty()) ? null : ticketList);
    }

    @GetMapping(value = "/title")
    @ResponseBody
    public List<String> showTicketsByTitle() throws NoContentException {
        log.info("Controller-Ticket-Get-FindAll");
        try{List<String> ticketList = ticketServiceImp.findAllTitle();
            return ((ticketList.isEmpty()) ? null : ticketList);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/{id}")
    @ResponseBody
    public void setStatus(@PathVariable Long id) throws NoContentException{
        log.info("Controller-Ticket-Post-SetStatus");
        Ticket ticket = ticketServiceImp.findById(id);
        ticket.setStatus(Status.seen);
        ticketServiceImp.edit(ticket);
    }


}

