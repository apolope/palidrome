package br.com.a3sitsolutions.utils.mappers;

import br.com.a3sitsolutions.exceptions.SaveException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class SaveExceptionMapper implements ExceptionMapper<SaveException> {
    @Override
    public Response toResponse(SaveException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), exception.httpStatusCode.code());

        return Response
                .status(exception.httpStatusCode.code())
                .entity(errorMessage)
                .build();
    }

    public static class ErrorMessage {
        public String message;
        public int statusCode;

        public ErrorMessage(String message, int statusCode) {
            this.message = message;
            this.statusCode = statusCode;
        }
    }
}
