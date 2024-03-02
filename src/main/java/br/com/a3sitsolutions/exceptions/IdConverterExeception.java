package br.com.a3sitsolutions.exceptions;

import br.com.a3sitsolutions.utils.Messages;
import io.netty.handler.codec.http.HttpResponseStatus;

public class IdConverterExeception extends BaseHttpException {
    public IdConverterExeception(String message, HttpResponseStatus httpStatusCode, Throwable throwable) {
        super(message, httpStatusCode, throwable);
    }

    public IdConverterExeception(String message, HttpResponseStatus httpStatusCode) {
        super(message, httpStatusCode);
    }

    public IdConverterExeception(HttpResponseStatus httpStatusCode, Messages messageEnum) {
        super(httpStatusCode, messageEnum);
    }

    public IdConverterExeception(HttpResponseStatus httpStatusCode, Messages messageEnum, String... args) {
        super(httpStatusCode, messageEnum, args);
    }

    public IdConverterExeception(Messages message, String id) {
        super(HttpResponseStatus.NOT_FOUND, message, new String[]{id});
    }
}
