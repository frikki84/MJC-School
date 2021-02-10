package com.epam.esm.service.mapper;


import com.epam.esm.service.GiftCertificate;
import com.epam.esm.service.Tag;
import com.epam.esm.service.dto.CertificateDto;

import java.util.List;


public interface CertificateDtoMapper {
    public GiftCertificate changeDtoToCertificate(CertificateDto dto);

    public CertificateDto changeCertificateToDto(GiftCertificate certificate, List<Tag> tagList);




}
