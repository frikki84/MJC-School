package com.epam.esm.validation;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.Tag;
import com.epam.esm.exception.*;
import com.epam.esm.service.exception.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class CertificateDTOCheckingTest {
    private List<Tag> tagList = Arrays.asList(new Tag("dance"), new Tag("active"));
    private CertificateDto dto = new CertificateDto("Dancing", "Dancing cource", new BigDecimal(150)
            , 40, tagList);

    @Test
    public void chechCertificateDtoFormat() {
        boolean resultList = CertificateDTOChecking.chechCertificateDtoFormat(dto);
        Assertions.assertTrue(resultList);
    }

    @Test
    public void chechCertificateDtoFormatWrongName() {
        dto.setName("");
       Throwable throwable = Assertions.assertThrows(InvalidCertificateDtoName.class, () ->
               CertificateDTOChecking.chechCertificateDtoFormat(dto));
       Assertions.assertEquals(InvalidCertificateDtoName.class, throwable.getClass());

    }

    @Test
    public void chechCertificateDtoFormatWrongDescription() {
        dto.setDescription("qwe");
        Throwable throwable = Assertions.assertThrows(InvalidCertificateDtoDescription.class, () ->
                CertificateDTOChecking.chechCertificateDtoFormat(dto));
        Assertions.assertEquals(InvalidCertificateDtoDescription.class, throwable.getClass());

    }

    @Test
    public void chechCertificateDtoFormatWrongPrice() {
        dto.setPrice(new BigDecimal(-2));
        Throwable throwable = Assertions.assertThrows(InvalidCertificateDtoPrice.class, () ->
                CertificateDTOChecking.chechCertificateDtoFormat(dto));
        Assertions.assertEquals(InvalidCertificateDtoPrice.class, throwable.getClass());
    }

    @Test
    public void chechCertificateDtoFormatWrongDuration() {
        dto.setDuration(0);
        Throwable throwable = Assertions.assertThrows(InvalidCertificateDtoDuration.class, () ->
                CertificateDTOChecking.chechCertificateDtoFormat(dto));
        Assertions.assertEquals(InvalidCertificateDtoDuration.class, throwable.getClass());

    }

    @Test
    public void chechCertificateDtoFormatWrongTagList() {
        dto.setTagList(null);
        Throwable throwable = Assertions.assertThrows(InvalidCertificateDtoTagList.class, () ->
                CertificateDTOChecking.chechCertificateDtoFormat(dto));
        Assertions.assertEquals(InvalidCertificateDtoTagList.class, throwable.getClass());

    }


}