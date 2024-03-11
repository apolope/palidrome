package br.com.a3sitsolutions.exceptions;

import br.com.a3sitsolutions.utils.MessagesUtil;
import io.netty.handler.codec.http.HttpResponseStatus;

public class MatrixCoverterException extends BaseHttpException {
    public MatrixCoverterException(MessagesUtil message) {
        super(HttpResponseStatus.BAD_REQUEST, message);
    }
}
