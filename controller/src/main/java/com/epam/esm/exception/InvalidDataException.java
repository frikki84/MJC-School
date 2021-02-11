package com.epam.esm.exception;


import com.epam.esm.util.CustomErrorCode;

public class InvalidDataException extends RuntimeException {
    private String code;

    public InvalidDataException(CustomErrorCode code) {
        this.code = code.getCode();
    }

    public InvalidDataException(String message, CustomErrorCode code) {
        super(message);
        this.code = code.getCode();
    }

    public String getCode() {
        return code;
    }
}
