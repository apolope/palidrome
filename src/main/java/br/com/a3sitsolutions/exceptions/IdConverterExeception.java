package br.com.a3sitsolutions.exceptions;

import br.com.a3sitsolutions.utils.MessagesUtil;
import io.netty.handler.codec.http.HttpResponseStatus;

public class IdConverterExeception extends BaseHttpException {
    public IdConverterExeception(String message, HttpResponseStatus httpStatusCode, Throwable throwable) {
        super(message, httpStatusCode, throwable);
    }

    public IdConverterExeception(String message, HttpResponseStatus httpStatusCode) {
        super(message, httpStatusCode);
    }

    public IdConverterExeception(HttpResponseStatus httpStatusCode, MessagesUtil messageEnum) {
        super(httpStatusCode, messageEnum);
    }

    public IdConverterExeception(HttpResponseStatus httpStatusCode, MessagesUtil messageEnum, String... args) {
        super(httpStatusCode, messageEnum, args);
    }

    public IdConverterExeception(MessagesUtil message, String id) {
        super(HttpResponseStatus.BAD_REQUEST, message, new String[]{id});
    }
}
