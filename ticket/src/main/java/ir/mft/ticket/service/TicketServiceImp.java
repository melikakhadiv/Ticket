package ir.mft.ticket.service;

import ir.mft.ticket.model.Ticket;
import ir.mft.ticket.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TicketServiceImp implements TicketService {
    private TicketRepository ticketRepository;

    public TicketServiceImp(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        log.info("Service-Ticket-Save");
        ticket.setActive(true);
        ticket.setTicketDate(LocalDate.now());
        ticket.setTicketTime(LocalTime.now());
        log.info(ticket.toString());
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public Ticket edit(Ticket ticket) {
        log.info("Service-Ticket-Edit");
        ticket.setActive(true);
        if (findById(ticket.getId()) != null) {
            log.info("Service-Ticket-Edit" + ticket);
            ticketRepository.save(ticket);
            return ticket;
        } else return null;
    }

    @Override
    public Ticket remove(Ticket ticket) {
        log.info("Service-Ticket-Remove");
        if (findById(ticket.getId()) != null) {
            ticketRepository.delete(ticket);
            return ticket;
        } else return null;
    }

    @Override
    @Transactional
    public Ticket logicalRemove(Long id) {
        log.info("Service-Ticket-LogicalRemove");
        Ticket ticket = findById(id);
        log.info("Service-Ticket-LogicalRemove: " + ticket);
        if (ticket != null) {
            ticket.setActive(false);
            ticketRepository.save(ticket);
            return ticket;
        } else return null;
    }

    @Override
    public List<Ticket> findAll() {
        log.info("Service-Ticket-FindAll");
        List<Ticket> ticketList = ticketRepository.findAll();
        return ticketList;
    }

    @Override
    public Ticket findById(Long id) {
        log.info("Service-Ticket-FindById");
        Optional<Ticket> ticket = ticketRepository.findById(id);
        log.info("Service-Ticket-FindById: " + ticket.get());
        return (ticket.isPresent() ? ticket.get() : null);
    }

    @Override
    public List<Ticket> findByApplicant(String applicant) {
        log.info("Service-Ticket-FindByApplicant");
        List<Ticket> ticketList = ticketRepository.findByApplicant(applicant);
        return ticketList;
    }

    @Override
    public List<Ticket> findByDate(LocalDate timeStamp) {
        log.info("Service-Ticket-FindByDate");
        List<Ticket> ticketList = ticketRepository.findByDate(timeStamp);
        return ticketList;
    }
}
