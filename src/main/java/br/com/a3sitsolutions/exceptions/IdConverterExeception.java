package br.com.a3sitsolutions.exceptions;

import br.com.a3sitsolutions.utils.MessagesUtil;
import io.netty.handler.codec.http.HttpResponseStatus;

public class IdConverterExeception extends BaseHttpException {

    public IdConverterExeception(MessagesUtil message, String id) {
        super(HttpResponseStatus.BAD_REQUEST, message, new String[]{id});
    }
}
