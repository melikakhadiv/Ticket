package ir.mft.ticket.service;


import ir.mft.ticket.enums.Status;
import ir.mft.ticket.exceptions.NoContentException;
import ir.mft.ticket.model.Ticket;
import ir.mft.ticket.repository.TicketRepository;
import ir.mft.ticket.service.imp.TicketService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TicketServiceImp implements TicketService {
    private final TicketRepository ticketRepository;

    public TicketServiceImp(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket save(Ticket ticket) throws NoContentException {
        log.info("Service-Ticket-Save");
        ticket.setStatus(Status.postponed);
        ticket.setDeleted(false);
        ticket.setTicketTimeStamp(LocalDateTime.now());
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    @Transactional
    public Ticket edit(Ticket ticket) throws NoContentException {
        log.info("Service-Ticket-Edit");
        ticketRepository.findById(ticket.getId()).orElseThrow(
                () -> new NoContentException("No Ticket Found with id : " + ticket.getId()));
        return ticketRepository.save(ticket);
    }


    @Override
    @Transactional
    public Ticket editStatusById(Long id, Status status) throws NoContentException {
        log.info("Service-Ticket-Edit");
        Optional<Ticket> ticket = ticketRepository.findById(id);
        ticket.orElseThrow(
                () -> new NoContentException("No Ticket Found with id : " + id));
        ticket.get().setStatus(status);
        return ticketRepository.save(ticket.get());
    }

    @Override
    public void remove(Ticket ticket) throws NoContentException {
        log.info("Service-Ticket-Remove");
        ticketRepository.findById(ticket.getId()).orElseThrow(
                () -> new NoContentException("No Ticket Found with id : " + ticket.getId()));
        ticketRepository.delete(ticket);
    }

    @Override
    @Transactional
    public Ticket logicalRemove(Long id) throws NoContentException {
        log.info("Service-Ticket-LogicalRemove");
        Ticket ticket = ticketRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Ticket Found with id : " + id));
        ticket.setDeleted(true);
        return ticketRepository.save(ticket);

    }

    @Override
    public List<Ticket> findAll() {
        log.info("Service-Ticket-FindAll");
        List<Ticket> ticketList = ticketRepository.findAll();
        return ticketList;
    }

    @Override
    public List<Ticket> findAllDeletedFalse() {
        log.info("Service-Ticket-FindAllDeletedFalse");
        List<Ticket> ticketList = ticketRepository.findAllDeletedFalse();
        return ticketList;
    }

    @Override
    public List<String> findAllTitle() {
        log.info("Service-Ticket-FindAllTitle");
        List<String> ticketList = ticketRepository.findAllTitle();
        return ticketList;
    }

    @Override
    public List<Ticket> findByTitle(String title) {
        log.info("Service-Ticket-FindByTitle");
        List<Ticket> ticketList = ticketRepository.findByTitle(title);
        return ticketList;
    }

    @Override
    public Ticket findById(Long id) throws NoContentException {
        log.info("Service-Ticket-FindById");
        return ticketRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Ticket Found with id : " + id)
        );
    }



    @Override
    public List<Ticket> findByDate(LocalDateTime timeStamp) throws NoContentException {
        log.info("Service-Ticket-FindByDate");
        List<Ticket> ticketList = ticketRepository.findByDate(timeStamp);
        if (!ticketList.isEmpty()) {
            return ticketList;
        } else throw new NoContentException("Ticket Not Found !");
    }
}
