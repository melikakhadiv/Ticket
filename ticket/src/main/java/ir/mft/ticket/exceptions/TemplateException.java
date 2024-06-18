package ir.mft.ticket.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TemplateException extends Exception {
    private int statusCode;
    private String statusText;

    public TemplateException(String message)  {
        super(message);
        statusText = message;
    }
}
