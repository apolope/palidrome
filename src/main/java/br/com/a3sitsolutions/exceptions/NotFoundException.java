package br.com.a3sitsolutions.exceptions;

import br.com.a3sitsolutions.utils.MessagesUtil;
import io.netty.handler.codec.http.HttpResponseStatus;

public class NotFoundException extends BaseHttpException {

    public NotFoundException() {
        super(HttpResponseStatus.NOT_FOUND, MessagesUtil.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message, HttpResponseStatus.NOT_FOUND);
    }

    public NotFoundException(String message, Throwable throwable) {
        super(message, HttpResponseStatus.NOT_FOUND, throwable);
    }

    public NotFoundException(MessagesUtil message, String id) {
        super(HttpResponseStatus.NOT_FOUND, message, new String[]{id});
    }

    public NotFoundException(MessagesUtil messageEnum, Throwable detailMessage, String... args) {
        super(HttpResponseStatus.NOT_FOUND, messageEnum, detailMessage, args);
    }

}
