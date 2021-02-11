package com.epam.esm.exception;

import com.epam.esm.util.CustomErrorCode;

public class NoUserTag  extends RuntimeException {
    private String code;

    public NoUserTag(CustomErrorCode code) {
        this.code = code.getCode();
    }

    public NoUserTag(String message, CustomErrorCode code) {
        super(message);
        this.code = code.getCode();
    }

    public String getCode() {
        return code;
    }
}
