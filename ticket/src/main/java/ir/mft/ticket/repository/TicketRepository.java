package ir.mft.ticket.repository;

import ir.mft.ticket.model.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    @Query("select oo from ticketEntity oo where oo.applicant=:applicant and oo.active=true")
    List<Ticket> findByApplicant(String applicant);

    @Query("select oo from ticketEntity oo where oo.ticketDate=:ticketDate and oo.active=true")
    List<Ticket> findByDate(LocalDate ticketDate);

//    @Transactional
//    @Modifying
//    @Query("update  Ticket t set t.active=false where t.id=:id")
//    void logicalDelete(Long id);
}
