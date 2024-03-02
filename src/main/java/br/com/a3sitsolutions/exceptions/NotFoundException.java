package br.com.a3sitsolutions.exceptions;

import br.com.a3sitsolutions.utils.Messages;
import io.netty.handler.codec.http.HttpResponseStatus;

public class NotFoundException extends BaseHttpException {

    public NotFoundException() {
        super(HttpResponseStatus.NOT_FOUND, Messages.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message, HttpResponseStatus.NOT_FOUND);
    }

    public NotFoundException(String message, Throwable throwable) {
        super(message, HttpResponseStatus.NOT_FOUND, throwable);
    }

    public NotFoundException(Messages message, String id) {
        super(HttpResponseStatus.NOT_FOUND, message, new String[]{id});
    }

    public NotFoundException(Messages messageEnum, Throwable detailMessage, String... args) {
        super(HttpResponseStatus.NOT_FOUND, messageEnum, detailMessage, args);
    }

}
