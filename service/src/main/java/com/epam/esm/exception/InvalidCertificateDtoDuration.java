package com.epam.esm.exception;

import com.epam.esm.util.CustomErrorCode;

public class InvalidCertificateDtoDuration extends RuntimeException {
    private String code;

    public InvalidCertificateDtoDuration(CustomErrorCode code) {
        this.code = code.getCode();
    }

    public InvalidCertificateDtoDuration(String message, CustomErrorCode code) {
        super(message);
        this.code = code.getCode();
    }

    public String getCode() {
        return code;
    }
}
