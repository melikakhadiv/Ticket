package ir.mft.ticket.service;

import ir.mft.ticket.model.Response;


import java.time.LocalDate;
import java.util.List;

public interface ResponseService {
    Response save(Response response);
    Response edit(Response response);
    Response remove(Response response);
    Response logicalRemove(Long id);
    List<Response> findAll();
    Response findById(Long id);
    List<Response> findByDate(LocalDate responseDate);
    List<Response> findByResponder(String responder);
}
