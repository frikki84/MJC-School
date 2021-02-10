package com.epam.esm.exception;

import com.epam.esm.util.CustomErrorCode;

public class InvalidCertificateDtoTagList extends RuntimeException {
    private String code;

    public InvalidCertificateDtoTagList(CustomErrorCode code) {
        this.code = code.getCode();
    }

    public InvalidCertificateDtoTagList(String message, CustomErrorCode code) {
        super(message);
        this.code = code.getCode();
    }

    public String getCode() {
        return code;
    }
}