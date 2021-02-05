package com.epam.esm.exception;

import com.epam.esm.util.CustomErrorCode;

public class InvalidCertificateDtoDescription extends RuntimeException {
    private String code;

    public InvalidCertificateDtoDescription(CustomErrorCode code) {
        this.code = code.getCode();
    }

    public InvalidCertificateDtoDescription(String message, CustomErrorCode code) {
        super(message);
        this.code = code.getCode();
    }

    public String getCode() {
        return code;
    }
}