package com.epam.esm;


import com.epam.esm.dto.CertificateDto;
import com.epam.esm.exception.InvalidDataException;
import com.epam.esm.util.CustomErrorCode;
import com.epam.esm.validation.CertificateDTOChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/certificates")
public class CertificatesController {
    private final CertificateService certificateService;
    private final CertificateTagService certificateTagService;

    @Autowired
    public CertificatesController(CertificateService certificateService, CertificateTagService certificateTagService) {
        this.certificateService = certificateService;
        this.certificateTagService = certificateTagService;
    }


    @GetMapping()
    public List<GiftCertificate> findAllCertificates() {
        List<GiftCertificate> fullCertificateList = certificateService.findAllCertificates();
        return fullCertificateList;
    }

    @GetMapping("/{id}")
    public GiftCertificate findCertificateById(@PathVariable("id") long id) {
        GiftCertificate certificate = certificateService.findCertificateById(id);
        return certificate;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createNewCertificate(CertificateDto certificateDto) {
        boolean dtoChecking = CertificateDTOChecking.chechCertificateDtoFormat(certificateDto);
       Integer id =  certificateTagService.createNewCertificateWithTags(certificateDto);
       return  id;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Integer updateCertificate(GiftCertificate certificate, @PathVariable("id") long id)  {
        Integer updatesNumber = certificateService.updateCertificate(certificate, id);
        return updatesNumber;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Integer deleteCertificate(@PathVariable("id") int id) {
        Integer deleteFields = certificateService.deleteCertificate(id);
        return deleteFields;
    }

    @GetMapping("/tag={tagName}")
    public List<CertificateDto> findCertificatesByTag(@PathVariable("tagName") String tagName) {
        List<CertificateDto> certificateList = certificateService.findCertificatesByTagName(tagName);
        return certificateList;
    }

    @GetMapping("/sort_by=name(asc)")
    public List<CertificateDto> sortCertificatesByNameAsc() {
        List<CertificateDto> certificateList = certificateService.sortAllCertificatesByNameAsc();
        return certificateList;
    }


    @GetMapping("/name={namePart}")
    public List<CertificateDto> findAllCertificatesWithTagsByNameOrDescriptionPart(@PathVariable("namePart") String namePart) {
        List<CertificateDto> certificateList = certificateService.findAllCertificatesByNameDescriptionPart(namePart);
        return certificateList;
    }
}


