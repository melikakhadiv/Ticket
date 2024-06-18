package ir.mft.ticket.service;

import ir.mft.ticket.exceptions.NoContentException;
import ir.mft.ticket.model.TicketGroup;
import ir.mft.ticket.repository.TicketGroupRepository;
import ir.mft.ticket.service.imp.TicketGroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketGroupServiceImp implements TicketGroupService {
    private TicketGroupRepository ticketGroupRepository;

    public TicketGroupServiceImp(TicketGroupRepository ticketGroupRepository) {
        this.ticketGroupRepository = ticketGroupRepository;
    }

    @Override
    public TicketGroup save(TicketGroup ticketGroup) {
        ticketGroupRepository.save(ticketGroup);
        return ticketGroup;
    }

    @Override
    public TicketGroup edit(TicketGroup ticketGroup) {
        ticketGroupRepository.save(ticketGroup);
        return ticketGroup;
    }

    @Override
    public TicketGroup remove(TicketGroup ticketGroup) {
        ticketGroupRepository.delete(ticketGroup);
        return ticketGroup;
    }

    @Override
    public TicketGroup findById(Long id) throws NoContentException {
        return ticketGroupRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Ticket Group Found with id : " + id)
        );

    }

    @Override
    public List<TicketGroup> findAll() {
        List<TicketGroup> ticketGroupList = ticketGroupRepository.findAll();
        return ticketGroupList;
    }

    @Override
    public TicketGroup findByTitle(String title) {
//todo : orElse ??
        Optional<TicketGroup> group = Optional.ofNullable(ticketGroupRepository.findByTitle(title));
        return (group.isPresent()) ? group.get() : null;
    }

    @Override
    public List<TicketGroup> findByParentId(Long id) {
        List<TicketGroup> ticketGroupList = ticketGroupRepository.findByParentId(id);
        return ticketGroupList;
    }

    @Override
    public List<TicketGroup> findByParentRoot() {
        List<TicketGroup> ticketGroupList = ticketGroupRepository.findByParentRoot();
        return ticketGroupList;
    }
}
