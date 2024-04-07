package ir.mft.ticket.repository;

import ir.mft.ticket.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ResponseRepository extends JpaRepository<Response , Long> {
    @Query("select oo from responseEntity oo where oo.responseDate=:responseDate")
    List<Response> findByDate(LocalDate responseDate);

    @Query("select oo from responseEntity oo where oo.responder=:responder")
    List<Response> findByResponder(String responder);

//    @Query("update  responseEntity set active=false where id=:id")
//    Response logicalDelete(Long id);
}
