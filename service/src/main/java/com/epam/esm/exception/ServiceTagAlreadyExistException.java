package com.epam.esm.exception;

import com.epam.esm.util.CustomErrorCode;

public class ServiceTagAlreadyExistException extends RuntimeException {

    private String code;

    public ServiceTagAlreadyExistException(CustomErrorCode code) {
        this.code = code.getCode();
    }

    public ServiceTagAlreadyExistException(String message, CustomErrorCode code) {
        super(message);
        this.code = code.getCode();
    }

    public String getCode() {
        return code;
    }
}

