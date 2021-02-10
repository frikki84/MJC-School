package com.epam.esm.exception;


import com.epam.esm.util.CustomErrorCode;

public class TagAlreadyExistsException extends RuntimeException {
    private String code;

    public TagAlreadyExistsException(CustomErrorCode code) {
        this.code = code.getCode();
    }

    public TagAlreadyExistsException(String message, CustomErrorCode code) {
        super(message);
        this.code = code.getCode();
    }

    public String getCode() {
        return code;
    }
}
