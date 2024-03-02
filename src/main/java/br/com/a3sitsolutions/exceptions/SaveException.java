package br.com.a3sitsolutions.exceptions;

import br.com.a3sitsolutions.utils.Messages;
import io.netty.handler.codec.http.HttpResponseStatus;

public class SaveException extends BaseHttpException {

    public SaveException() {
        super(HttpResponseStatus.BAD_REQUEST, Messages.SAVE_PROBLEM);
    }

    public SaveException(String message) {
        super(message, HttpResponseStatus.BAD_REQUEST);
    }

    public SaveException(String message, Throwable throwable) {
        super(message, HttpResponseStatus.BAD_REQUEST, throwable);
    }

}
