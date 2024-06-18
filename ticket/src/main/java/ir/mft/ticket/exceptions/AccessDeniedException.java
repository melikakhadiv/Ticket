package ir.mft.ticket.exceptions;

public class AccessDeniedException extends TemplateException{

    public AccessDeniedException(String message){
    super(message);
    setStatusCode(403);
}
}
