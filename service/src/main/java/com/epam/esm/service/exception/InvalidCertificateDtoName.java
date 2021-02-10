package com.epam.esm.service.exception;

import com.epam.esm.service.util.CustomErrorCode;

public class InvalidCertificateDtoName  extends RuntimeException {
    private String code;

    public InvalidCertificateDtoName(CustomErrorCode code) {
        this.code = code.getCode();
    }

    public InvalidCertificateDtoName(String message, CustomErrorCode code) {
        super(message);
        this.code = code.getCode();
    }

    public String getCode() {
        return code;
    }
}
