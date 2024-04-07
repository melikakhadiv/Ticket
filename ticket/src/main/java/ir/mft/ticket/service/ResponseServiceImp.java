package ir.mft.ticket.service;

import ir.mft.ticket.model.Response;
import ir.mft.ticket.repository.ResponseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ResponseServiceImp implements ResponseService{
    private ResponseRepository responseRepository;

    public ResponseServiceImp(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    @Override
    public Response save(Response response) {
        log.info("Service-Response-Save");
        response.setActive(true);
        responseRepository.save(response);
        return response;
    }

    @Override
    public Response edit(Response response) {
        log.info("Service-Response-Edit");
        response.setActive(true);
        if (findById(response.getId()) != null){
        responseRepository.save(response);
        return response;
        }
        else return null;
    }

    @Override
    public Response remove(Response response) {
        log.info("Service-Response-Remove");
        if (findById(response.getId()) != null){
            responseRepository.delete(response);
            return response;
        }
        else return null;
    }

    @Override
    public Response logicalRemove(Long id) {
        log.info("Service-Response-LogicalRemove");
        Response response = findById(id);
        if (response != null){
            response.setActive(false);
            responseRepository.save(response);
            return response;
        }
        else return null;
    }

    @Override
    public List<Response> findAll() {
        log.info("Service-Response-FindAll");
        List<Response> responseList = responseRepository.findAll();
        return responseList;
    }

    @Override
    public Response findById(Long id) {
        log.info("Service-Response-FindById");
        Optional<Response> response = responseRepository.findById(id);
        return (response.isPresent() ? response.get() : null);
    }

    @Override
    public List<Response> findByDate(LocalDate responseDate) {
        log.info("Service-Response-FindByDate");
        List<Response> responseList = responseRepository.findByDate(responseDate);
        return responseList;
    }

    @Override
    public List<Response> findByResponder(String responder) {
        log.info("Service-Response-FindByResponder");
        List<Response> responseList = responseRepository.findByResponder(responder);
        return responseList;
    }
}
