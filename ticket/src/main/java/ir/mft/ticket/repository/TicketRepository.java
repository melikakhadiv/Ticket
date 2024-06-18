package ir.mft.ticket.repository;



import ir.mft.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    @Query("select oo from ticketEntity oo order by oo.id")
    List<Ticket> findAll();


    @Query("select oo from ticketEntity oo where oo.ticketTimeStamp=:ticketDate and oo.deleted=false")
    List<Ticket> findByDate(LocalDateTime ticketDate);

    @Query("select oo from ticketEntity oo where oo.group.title=:title and oo.deleted=false")
    List<Ticket> findByTitle(String title);

    @Query("select oo from ticketEntity oo where  oo.deleted=false ")
    List<Ticket> findAllDeletedFalse();

    @Query("select distinct oo.group.title from ticketEntity oo ")
    List<String> findAllTitle();



}
