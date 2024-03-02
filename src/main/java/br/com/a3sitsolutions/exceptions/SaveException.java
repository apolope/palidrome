package br.com.a3sitsolutions.exceptions;

import br.com.a3sitsolutions.utils.translationsn.Translator;
import io.netty.handler.codec.http.HttpResponseStatus;

public class SaveException extends RuntimeException{

    public String message;

    public HttpResponseStatus httpStatusCode = HttpResponseStatus.BAD_REQUEST;

    public SaveException(){

        super(Translator.toLocale("http_messages","save.problem"));
    }

    public SaveException(String message){
        super(message);
        this.message = message;
    }

    public SaveException(String message, Throwable throwable){
        super(message, throwable);
        this.message = message;
    }

    public SaveException(Throwable throwable){
        super(throwable);
    }

    public SaveException(String message, HttpResponseStatus httpStatusCode){
        super(message);
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

    public SaveException(String message, HttpResponseStatus httpStatusCode, Throwable throwable){
        super(message, throwable);
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }
}
