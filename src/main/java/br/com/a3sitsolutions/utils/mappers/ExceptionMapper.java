package br.com.a3sitsolutions.utils.mappers;

import br.com.a3sitsolutions.exceptions.BaseHttpException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionMapper implements jakarta.ws.rs.ext.ExceptionMapper<BaseHttpException> {

    @Override
    public Response toResponse(BaseHttpException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), exception.getHttpStatusCode().code());

        return Response
                .status(exception.getHttpStatusCode().code())
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
