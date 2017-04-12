
package com.gandhre.kunal.example.exception;

public class NotFoundException extends ApiException {
    private static final long serialVersionUID = -3224900536864029703L;

    public NotFoundException(int code, String msg) {
        super(code, msg);
    }
}
