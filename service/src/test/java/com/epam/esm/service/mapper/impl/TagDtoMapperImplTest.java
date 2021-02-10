package com.epam.esm.service.mapper.impl;

import com.epam.esm.service.Tag;
import com.epam.esm.service.dto.CertificateDto;
import com.epam.esm.service.dto.TagDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class TagDtoMapperImplTest {
    private TagDto tagDto = new TagDto(1, "dance");
    private Tag tag = new Tag(1, "dance");
    private List<Tag> tagList = Arrays.asList(new Tag("dance"), new Tag("active"));
    private CertificateDto dto = new CertificateDto("Dancing", "Dancing cource", new BigDecimal(150)
            , 40, tagList);

    TagDtoMapperImpl mapper = new TagDtoMapperImpl();

    @Test
    void changeTagDtoToTag() {
        Tag testTag = mapper.changeTagDtoToTag(tagDto);
        Assertions.assertEquals(tag, testTag);
    }

    @Test
    void changeCertificateDtoToTagList() {
        List<Tag> testList = mapper.changeCertificateDtoToTagList(dto);
        Assertions.assertEquals(tagList, testList);
    }
}