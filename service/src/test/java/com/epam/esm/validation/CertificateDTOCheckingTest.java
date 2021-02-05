package com.epam.esm.validation;

import com.epam.esm.Tag;
import com.epam.esm.dto.CertificateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CertificateDTOCheckingTest {
    private List<Tag> tagList = Arrays.asList(new Tag("dance"), new Tag("active"));
    private CertificateDto dto = new CertificateDto("Dancing", "Dancing cource", new BigDecimal(150)
            , 40, tagList);

    @Test
    public  void chechCertificateDtoFormat() {
        boolean resultList = CertificateDTOChecking.chechCertificateDtoFormat(dto);
        Assertions.assertNull(resultList);
    }


}