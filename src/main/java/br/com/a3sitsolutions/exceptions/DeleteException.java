package br.com.a3sitsolutions.exceptions;

import br.com.a3sitsolutions.utils.MessagesUtil;
import io.netty.handler.codec.http.HttpResponseStatus;

public class DeleteException extends BaseHttpException {

    public DeleteException(MessagesUtil message, String id) {
        super(HttpResponseStatus.INTERNAL_SERVER_ERROR, message, new String[]{id});
    }
}
