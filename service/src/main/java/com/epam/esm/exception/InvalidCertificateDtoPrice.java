package com.epam.esm.exception;

import com.epam.esm.util.CustomErrorCode;

public class InvalidCertificateDtoPrice extends RuntimeException {
    private String code;

    public InvalidCertificateDtoPrice(CustomErrorCode code) {
        this.code = code.getCode();
    }

    public InvalidCertificateDtoPrice(String message, CustomErrorCode code) {
        super(message);
        this.code = code.getCode();
    }

    public String getCode() {
        return code;
    }
}
