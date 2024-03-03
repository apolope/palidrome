package br.com.a3sitsolutions.exceptions;

import br.com.a3sitsolutions.utils.MessagesUtil;
import io.netty.handler.codec.http.HttpResponseStatus;

public class NotFoundException extends BaseHttpException {

    public NotFoundException(MessagesUtil message, String id) {
        super(HttpResponseStatus.NOT_FOUND, message, new String[]{id});
    }

}
