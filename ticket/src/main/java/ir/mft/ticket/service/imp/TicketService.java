package ir.mft.ticket.service.imp;



import ir.mft.ticket.enums.Status;
import ir.mft.ticket.exceptions.NoContentException;
import ir.mft.ticket.model.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {
    Ticket save(Ticket ticket) throws NoContentException;
    Ticket edit(Ticket ticket) throws NoContentException;
    public Ticket editStatusById(Long id, Status status) throws NoContentException;
        void remove(Ticket ticket) throws NoContentException;
    Ticket logicalRemove(Long id) throws NoContentException;
    List<Ticket> findAll();
    List<Ticket> findAllDeletedFalse();
    List<String> findAllTitle();
    List<Ticket> findByTitle(String title);
    Ticket findById(Long id) throws NoContentException;
//    List<Ticket> findByApplicant(Person applicant);
    List<Ticket> findByDate(LocalDateTime timeStamp) throws NoContentException;

}
