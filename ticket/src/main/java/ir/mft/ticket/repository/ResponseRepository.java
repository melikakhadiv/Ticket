package ir.mft.ticket.repository;


import ir.mft.ticket.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    @Query("select oo from responseEntity oo where oo.responseTimeStamp=:responseDate and oo.deleted=false ")
    List<Response> findByDate(LocalDateTime responseDate);


    @Query("select oo from responseEntity oo where oo.ticket.group.title=:title")
    List<Response> findByTicketGroup(String title);


}
