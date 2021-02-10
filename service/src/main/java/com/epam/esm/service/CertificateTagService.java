package com.epam.esm.service;

import com.epam.esm.service.dto.CertificateDto;
import com.epam.esm.service.mapper.CertificateDtoMapper;
import com.epam.esm.service.mapper.TagDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CertificateTagService {
    public static final Integer FIRST_VALUE_FOR_LOOP =0;

    private final CertificateDtoMapper certificateMapper;
    private final TagDtoMapper tagDtoMapper;
    private final CertificateService certificateService;
    private final TagService tagService;
    private final CertificateTagDao certificateTagDao;

    @Autowired
    public CertificateTagService(CertificateDtoMapper certificateMapper, TagDtoMapper tagDtoMapper, CertificateService certificateService, TagService tagService, CertificateTagDao certificateTagDao) {
        this.certificateMapper = certificateMapper;
        this.tagDtoMapper = tagDtoMapper;
        this.certificateService = certificateService;
        this.tagService = tagService;
        this.certificateTagDao = certificateTagDao;
    }


    public Integer createNewCertificateWithTags(CertificateDto dto)  {
        GiftCertificate certificate = certificateMapper.changeDtoToCertificate(dto);
        long certificateId = certificateService.createNewCertificate(certificate);

        List<Tag> tagList = tagDtoMapper.changeCertificateDtoToTagList(dto);

        Integer resultField = FIRST_VALUE_FOR_LOOP;

        for (Tag tag : tagList) {
            long tagId = 0;
            if (tagService.findTag(tag.getNameTag()) == null) {
                tagId = tagService.addNewTag(tag).getId();

            } else {
                tagId = tagService.findTag(tag.getNameTag()).getId();
            }

            resultField += certificateTagDao.createNewCertificateTagRelation(certificateId, tagId);


        }
        return resultField;
    }
}




