package br.com.a3sitsolutions.exceptions;

import br.com.a3sitsolutions.utils.MessagesUtil;
import io.netty.handler.codec.http.HttpResponseStatus;

public class SaveException extends BaseHttpException {

    public SaveException() {
        super(HttpResponseStatus.BAD_REQUEST, MessagesUtil.SAVE_PROBLEM);
    }

    public SaveException(String message) {
        super(message, HttpResponseStatus.BAD_REQUEST);
    }

    public SaveException(MessagesUtil messageEnum) {
        super(HttpResponseStatus.BAD_REQUEST, messageEnum);
    }
}
