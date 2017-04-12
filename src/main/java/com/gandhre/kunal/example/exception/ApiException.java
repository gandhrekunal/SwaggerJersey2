package com.gandhre.kunal.example.exception;

/**
 * @author
 * @since 1.1.0.137
 * @Created Apr 12, 2017
 */
public class ApiException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 2130008574329184227L;
    private int code;

    public ApiException(int code, String msg) {
        super(msg);
        this.setCode(code);
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }
}
