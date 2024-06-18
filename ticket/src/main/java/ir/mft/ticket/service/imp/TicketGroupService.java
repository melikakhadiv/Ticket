package ir.mft.ticket.service.imp;



import ir.mft.ticket.exceptions.NoContentException;
import ir.mft.ticket.model.TicketGroup;

import java.util.List;

public interface TicketGroupService {
    TicketGroup save(TicketGroup ticketGroup);
    TicketGroup edit(TicketGroup ticketGroup);
    TicketGroup remove(TicketGroup ticketGroup);
    TicketGroup findById(Long id) throws NoContentException;
    List<TicketGroup> findAll();
    TicketGroup findByTitle(String title);
    List<TicketGroup> findByParentId(Long id);
    List<TicketGroup> findByParentRoot();

}
