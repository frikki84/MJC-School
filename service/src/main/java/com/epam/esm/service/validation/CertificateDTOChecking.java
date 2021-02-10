package com.epam.esm.service.validation;


import com.epam.esm.service.dto.CertificateDto;
import com.epam.esm.service.exception.*;
import com.epam.esm.service.util.CustomErrorCode;

import java.math.BigDecimal;

public class CertificateDTOChecking {
    public static final boolean DEFAULT_RESULT = true;

    public static boolean chechCertificateDtoFormat(CertificateDto dto) {
        boolean result = DEFAULT_RESULT;
        if (dto.getName() == null || dto.getName().length() < 3 || dto.getName().length() >32) {
            throw new InvalidCertificateDtoName(CustomErrorCode.CERTIFICATE);
        } else if (dto.getDescription() == null || dto.getDescription().length() < 5 || dto.getDescription().length() > 100) {
            throw new InvalidCertificateDtoDescription(CustomErrorCode.CERTIFICATE);
        } else if (dto.getPrice() == null || dto.getPrice().compareTo(new BigDecimal("0")) < 0) {
            throw new InvalidCertificateDtoPrice(CustomErrorCode.CERTIFICATE);
        } else if (dto.getDuration() == null || dto.getDuration().compareTo(Integer.parseInt("1")) < 0){
            throw new InvalidCertificateDtoDuration(CustomErrorCode.CERTIFICATE);
        } else if (dto.getTagList() == null || dto.getTagList().size() == 0) {
            throw new InvalidCertificateDtoTagList(CustomErrorCode.CERTIFICATE);
        }

        return  result;
    }

}
