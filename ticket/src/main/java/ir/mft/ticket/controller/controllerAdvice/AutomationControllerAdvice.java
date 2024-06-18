package ir.mft.ticket.controller.controllerAdvice;


import ir.mft.ticket.exceptions.ExceptionWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AutomationControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> exceptionHandling(Exception e){
        return ResponseEntity
                .status(500)
                .body(ExceptionWrapper.getMessage(e));
    }
}
