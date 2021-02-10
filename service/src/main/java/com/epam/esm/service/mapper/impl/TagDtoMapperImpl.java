package com.epam.esm.service.mapper.impl;

import com.epam.esm.service.Tag;
import com.epam.esm.service.dto.CertificateDto;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.mapper.TagDtoMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagDtoMapperImpl implements TagDtoMapper {
    @Override
    public Tag changeTagDtoToTag(TagDto tagDto) {
        Tag tag = new Tag(tagDto.getIdTag(), tagDto.getNameTag());
        return tag;
    }


    @Override
    public List<Tag> changeCertificateDtoToTagList(CertificateDto dto) {
         List<Tag> tagList = dto.getTagList();
        return tagList;
    }
}
