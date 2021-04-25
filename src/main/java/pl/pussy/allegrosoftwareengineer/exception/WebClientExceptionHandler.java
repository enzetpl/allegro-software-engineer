package pl.pussy.allegrosoftwareengineer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class WebClientExceptionHandler {
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public MessageResponse handleException(HttpClientErrorException ex, HttpServletResponse response) {
        response.setStatus(ex.getRawStatusCode());
        return new MessageResponse(ex.getLocalizedMessage());

    }
}
