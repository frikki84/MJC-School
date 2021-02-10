package com.epam.esm.impl;
import com.epam.esm.CertificateDao;
import com.epam.esm.GiftCertificate;
import com.epam.esm.Tag;
import com.epam.esm.config.TestConfiguration;
import com.epam.esm.exception.NoSuchResourceException;
import com.epam.esm.exception.NoUserTag;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("dev")
class CertificateDaoImplTest {
    public static final int LIST_SIZE_FIND_ALL_CERTIFICATES = 4;
    public static final int ID_FIND_CERTIFICATE_BY_ID = 3;
    public static final int ID_FIND_CERTIFICATE_BY_ID_EXCEPTION = 1000;
    public static final int NEW_CERTIFICATE_ID = 5;
    public static final int UPDATED_CERTIFICATE_ID = 3;
    public static final int UPDATED_DB_FILDS_NUMBER= 1;
    public static final int UPDATED_CERTIFICATE_WRONG_ID = 3000;
    public static final String TAG_NAME = "sport";
    public static final String WRONG_TAG_NAME = "ROMM";

    private GiftCertificate giftCertificate = new GiftCertificate("Spa-comlex"
            , "Spa-complex for 1 person for 3 hours", new BigDecimal(150)
            , 40, LocalDateTime.of(2021, 01, 20, 13, 06, 22)
            , LocalDateTime.of(2021, 01, 20, 13, 06, 22));

    @Autowired
    private CertificateDao certificateDao;

    @BeforeEach
    public void beforeStart() {
        certificateDao = new CertificateDaoImpl(new JdbcTemplate(new DriverManagerDataSource()));
    }

    @Test
    void findAllCertificates() {
        List<GiftCertificate> list = certificateDao.findAllCertificates();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(LIST_SIZE_FIND_ALL_CERTIFICATES, list.size());
    }

    @Test
    void findCertificateById() {
        GiftCertificate certificate = certificateDao.findCertificateById(ID_FIND_CERTIFICATE_BY_ID);
        Assertions.assertNotNull(certificate);
        Assertions.assertEquals(ID_FIND_CERTIFICATE_BY_ID, certificate.getId());
    }

    @Test
    void findCertificateByIdWrong() {
        Throwable throwable = Assertions.assertThrows(NoSuchResourceException.class, () ->
                certificateDao.findCertificateById(ID_FIND_CERTIFICATE_BY_ID_EXCEPTION));
        Assertions.assertEquals(NoSuchResourceException.class, throwable.getClass());
    }

    @Test
    void createNewCertificate() {
        Integer id = certificateDao.createNewCertificate(giftCertificate);
        Assertions.assertNotNull(id);
        Assertions.assertEquals(NEW_CERTIFICATE_ID, id);
    }

    @Test
    void updateCertificate() {
        Integer number = certificateDao.updateCertificate(giftCertificate, UPDATED_CERTIFICATE_ID);
        Assertions.assertNotNull(number);
        Assertions.assertEquals(UPDATED_DB_FILDS_NUMBER, number);
        Assertions.assertEquals(giftCertificate.getName(), certificateDao.findCertificateById(UPDATED_CERTIFICATE_ID).getName());
    }

    @Test
    void updateCertificateWrongId() {
         Throwable throwable = Assertions.assertThrows(NoSuchResourceException.class, () ->
                certificateDao.updateCertificate(giftCertificate, UPDATED_CERTIFICATE_WRONG_ID));
        Assertions.assertEquals(NoSuchResourceException.class, throwable.getClass());
    }

    @Test
    void deleteCertificate() {
        Integer number = certificateDao.deleteCertificate(UPDATED_CERTIFICATE_ID);
        Assertions.assertNotNull(number);
        Assertions.assertEquals(UPDATED_DB_FILDS_NUMBER, number);

    }

    @Test
    void deleteCertificateWrongId() {
        Throwable throwable = Assertions.assertThrows(NoSuchResourceException.class, () -> {
            certificateDao.findCertificateById(UPDATED_CERTIFICATE_WRONG_ID);
        });
        Assertions.assertEquals(NoSuchResourceException.class, throwable.getClass());
    }

    @Test
    void findCertificatesByTag() {
              List<GiftCertificate> list = certificateDao.findCertificatesByTag(TAG_NAME);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(2, list.size());
    }

    @Test
    void findCertificatesByTagWrongTag() {
        Throwable throwable = Assertions.assertThrows(NoUserTag.class, () ->
                certificateDao.findCertificatesByTag(WRONG_TAG_NAME));
        Assertions.assertEquals(NoUserTag.class, throwable.getClass());
    }

    @Test
    void findCertificatesOrderedByNameAsc() {
        List<GiftCertificate> list = certificateDao.findCertificatesOrderedByNameAsc();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(LIST_SIZE_FIND_ALL_CERTIFICATES, list.size());
        Assertions.assertEquals(list.get(0).getId(), 3);
        Assertions.assertEquals(list.get(list.size()-1).getId(), 2);
    }


}