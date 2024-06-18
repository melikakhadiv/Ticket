package ir.mft.ticket.service;

import ir.mft.ticket.exceptions.NoContentException;
import ir.mft.ticket.model.Response;
import ir.mft.ticket.repository.ResponseRepository;
import ir.mft.ticket.service.imp.ResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ResponseServiceImp implements ResponseService {
    private ResponseRepository responseRepository;

    public ResponseServiceImp(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    @Override
    public Response save(Response response) {
        log.info("Service-Response-Save");
        response.setDeleted(true);
        response.setResponseTimeStamp(LocalDateTime.now());
        responseRepository.save(response);
        return response;
    }

    @Override
    public Response edit(Response response) throws NoContentException {
        log.info("Service-Response-Edit");
        responseRepository.findById(response.getId()).orElseThrow(
                () -> new NoContentException("No Response Found With Id : " + response.getId()));
        return responseRepository.save(response);

    }

    @Override
    public void remove(Response response) throws NoContentException {
        log.info("Service-Response-Remove");
        responseRepository.findById(response.getId()).orElseThrow(
                () -> new NoContentException("No Response Found With Id : " + response.getId()));
        responseRepository.delete(response);
    }

    @Override
    public Response logicalRemove(Long id) throws NoContentException {
        log.info("Service-Response-LogicalRemove");
        Response response = responseRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Response Found With Id : " + id));
        response.setDeleted(false);
        return responseRepository.save(response);
    }

    @Override
    public List<Response> findAll() {
        log.info("Service-Response-FindAll");
        List<Response> responseList = responseRepository.findAll();
        return responseList;
    }

    @Override
    public Response findById(Long id) throws NoContentException {
        log.info("Service-Response-FindById");
        return  responseRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Response Found With Id : " + id));
    }

    @Override
    public List<Response> findByDate(LocalDateTime responseDate) {
        log.info("Service-Response-FindByDate");
        List<Response> responseList = responseRepository.findByDate(responseDate);
        return responseList;
    }

    @Override
    public List<Response> findByTicketGroup(String title) {
        log.info("Service-Response-FindByTicketGroup");
        return responseRepository.findByTicketGroup(title);
    }
}
