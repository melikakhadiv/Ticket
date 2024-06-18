package ir.mft.ticket.controller;

import ir.mft.ticket.exceptions.NoContentException;
import ir.mft.ticket.model.TicketGroup;
import ir.mft.ticket.service.TicketGroupServiceImp;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(value = "/ticketGroup")
public class TicketGroupController {
    private TicketGroupServiceImp ticketGroupServiceImp;

    public TicketGroupController(TicketGroupServiceImp ticketGroupServiceImp) {
        this.ticketGroupServiceImp = ticketGroupServiceImp;
    }

    @GetMapping
    public String showTicketGroups(Model model) {
        log.info("Controller-TicketGroup-Get-FindAll");
        model.addAttribute("ticketGroup", new TicketGroup());
        model.addAttribute("ticketGroupList", ticketGroupServiceImp.findAll());
        model.addAttribute("ticketGroupParents", ticketGroupServiceImp.findByParentRoot());
        return "ticketGroup";
    }

    @PostMapping()
    @ResponseBody
    public TicketGroup saveTicket(@Valid TicketGroup ticketGroup, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Controller-TicketGroup-Post-Save-Error: " + ticketGroup.toString());
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        log.info("Controller-TicketGroup-Post-Save");
        return ticketGroupServiceImp.save(ticketGroup);
    }

    @PutMapping()
    @ResponseBody
    public TicketGroup editTicket(@Valid TicketGroup ticketGroup, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Controller-TicketGroup-Put-Edit-Error: " + ticketGroup.toString());
            throw new ValidationException(
                    bindingResult
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage()
                            ).collect(Collectors.toList()).toString()
            );
        }
        log.info("Controller-TicketGroup-Post-Edit");
        return ticketGroupServiceImp.edit(ticketGroup);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public TicketGroup deleteTicket(@PathVariable Long id) throws NoContentException {
        TicketGroup ticketGroup = ticketGroupServiceImp.findById(id);
        log.info("Controller-Ticket-Post-Delete: " + ticketGroup);
        return ticketGroupServiceImp.remove(ticketGroup);
    }


    @GetMapping(value = "/{id}")
    @ResponseBody
    public TicketGroup showTicket(@PathVariable("id") Long id) throws NoContentException {
        TicketGroup ticketGroup = ticketGroupServiceImp.findById(id);
        log.info("Controller-Ticket-Get-FindById-Ticket: " + ticketGroup);
        return ticketGroup;

    }


    @GetMapping(value = "/parent/{id}")
    @ResponseBody
    public List<TicketGroup> showTickets(@PathVariable("id") Long id) {
        List<TicketGroup> ticketGroups = ticketGroupServiceImp.findByParentId(id);
//        System.out.println(ticketGroups);
//        System.out.println(ticketGroups.get(0).getTitle());
//        System.out.println(ticketGroups.get(0).getParent());
        log.info("Controller-TicketGroup-Get-FindByParentId-TicketGroup");
        return (ticketGroups.isEmpty() ? null : ticketGroups);

    }


}
