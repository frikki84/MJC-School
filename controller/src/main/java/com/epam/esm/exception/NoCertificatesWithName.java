package com.epam.esm.exception;

import com.epam.esm.util.CustomErrorCode;

public class NoCertificatesWithName extends RuntimeException {
    private String code;

    public NoCertificatesWithName(CustomErrorCode code) {
        this.code = code.getCode();
    }

    public NoCertificatesWithName(String message, CustomErrorCode code) {
        super(message);
        this.code = code.getCode();
    }

    public String getCode() {
        return code;
    }
}