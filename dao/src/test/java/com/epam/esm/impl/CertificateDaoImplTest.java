package com.epam.esm.impl;
import com.epam.esm.CertificateDao;
import com.epam.esm.GiftCertificate;
import com.epam.esm.config.TestConfiguration;
import com.epam.esm.exception.NoSuchResourceException;
import com.epam.esm.exception.NoUserTag;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles("dev")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CertificateDaoImplTest {

    @Autowired
    private CertificateDao certificateDao;

    private GiftCertificate giftCertificate = new GiftCertificate("Spa-comlex"
            , "Spa-complex for 1 person for 3 hours", new BigDecimal(150)
            , 40, LocalDateTime.of(2021, 01, 20, 13, 06, 22)
            , LocalDateTime.of(2021, 01, 20, 13, 06, 22));

    @Test
    void findAllCertificates() {
        List<GiftCertificate> list = certificateDao.findAllCertificates();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(4, list.size());
    }

    @Test
    void findCertificateById() {
        long id = 3;
        GiftCertificate certificate = certificateDao.findCertificateById(id);
        Assertions.assertNotNull(certificate);
        Assertions.assertEquals(id, certificate.getId());
    }

    @Test
    void findCertificateByIdWrong() {
        long id = 31254;
        Throwable throwable = Assertions.assertThrows(NoSuchResourceException.class, () ->
                certificateDao.findCertificateById(id));
        Assertions.assertEquals(NoSuchResourceException.class, throwable.getClass());
    }

    @Test
    void createNewCertificate() {
        Integer id = certificateDao.createNewCertificate(giftCertificate);
        Assertions.assertNotNull(id);
        Assertions.assertEquals(5, id);
    }

    @Test
    void updateCertificate() {
        long id = 3;
        Integer number = certificateDao.updateCertificate(giftCertificate, id);
        Assertions.assertNotNull(number);
        Assertions.assertEquals(1, number);
        Assertions.assertEquals(giftCertificate.getName(), certificateDao.findCertificateById(id).getName());
    }

    @Test
    void updateCertificateWrongId() {
        long id = 3456;
        Throwable throwable = Assertions.assertThrows(NoSuchResourceException.class, () ->
                certificateDao.updateCertificate(giftCertificate, id));
        Assertions.assertEquals(NoSuchResourceException.class, throwable.getClass());
    }

    @Test
    void deleteCertificate() {
        long id = 2;
        Integer number = certificateDao.deleteCertificate(id);
        Assertions.assertNotNull(number);
        Assertions.assertEquals(1, number);

    }

    @Test
    void deleteCertificateWrongId() {
        long id = 221548;
        Throwable throwable = Assertions.assertThrows(NoSuchResourceException.class, () -> {
            certificateDao.findCertificateById(id);
        });
        Assertions.assertEquals(NoSuchResourceException.class, throwable.getClass());
    }

    @Test
    void findCertificatesByTag() {
        String tag = "sport";
        List<GiftCertificate> list = certificateDao.findCertificatesByTag(tag);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(2, list.size());
    }

    @Test
    void findCertificatesByTagWrongTag() {
        String tag = "carmen";
        Throwable throwable = Assertions.assertThrows(NoUserTag.class, () ->
                certificateDao.findCertificatesByTag(tag));
        Assertions.assertEquals(NoUserTag.class, throwable.getClass());
    }

    @Test
    void findCertificatesOrderedByNameAsc() {
        List<GiftCertificate> list = certificateDao.findCertificatesOrderedByNameAsc();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(4, list.size());
        Assertions.assertEquals(list.get(0).getId(), 3);
        Assertions.assertEquals(list.get(list.size()-1).getId(), 2);
    }


}