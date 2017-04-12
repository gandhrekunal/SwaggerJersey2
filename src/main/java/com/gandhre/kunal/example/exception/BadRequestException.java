package com.gandhre.kunal.example.exception;

public class BadRequestException extends ApiException {
    private static final long serialVersionUID = -8293818500353044763L;

    public BadRequestException(int code, String msg) {
        super(code, msg);
    }
}
