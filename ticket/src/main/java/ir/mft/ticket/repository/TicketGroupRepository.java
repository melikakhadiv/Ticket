package ir.mft.ticket.repository;


import ir.mft.ticket.model.TicketGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketGroupRepository extends JpaRepository<TicketGroup,Long> {
    @Query("select oo from ticketGroupEntity  oo where oo.parent.id=:id order by oo.id")
    List<TicketGroup> findByParentId(Long id);

    @Query("select oo from ticketGroupEntity oo where oo.title=:title")
    TicketGroup findByTitle(String title);

    @Query("select oo from ticketGroupEntity oo where oo.parent.id is null")
    List<TicketGroup> findByParentRoot();
}
