package ir.mft.ticket.exceptions;

public class DuplicateDataException extends TemplateException{

    /*409 is the correct status code for duplicate resource or resource already exists.*/

    public DuplicateDataException(String message){
        super(message);
        setStatusCode(409);
    }

}
