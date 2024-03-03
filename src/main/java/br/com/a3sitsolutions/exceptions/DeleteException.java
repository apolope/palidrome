package br.com.a3sitsolutions.exceptions;

import br.com.a3sitsolutions.utils.MessagesUtil;
import io.netty.handler.codec.http.HttpResponseStatus;

public class DeleteException extends BaseHttpException {

    public DeleteException() {
        super(HttpResponseStatus.INTERNAL_SERVER_ERROR, MessagesUtil.SAVE_PROBLEM);
    }

    public DeleteException(String message) {
        super(message, HttpResponseStatus.INTERNAL_SERVER_ERROR);
    }

    public DeleteException(String message, Throwable throwable) {
        super(message, HttpResponseStatus.INTERNAL_SERVER_ERROR, throwable);
    }

    public DeleteException(MessagesUtil message, String id) {
        super(HttpResponseStatus.INTERNAL_SERVER_ERROR, message, new String[]{id});
    }
}
