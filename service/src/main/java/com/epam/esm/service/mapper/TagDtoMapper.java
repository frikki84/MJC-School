package com.epam.esm.service.mapper;


import com.epam.esm.service.Tag;
import com.epam.esm.service.dto.CertificateDto;
import com.epam.esm.service.dto.TagDto;

import java.util.List;


public interface TagDtoMapper {
    public Tag changeTagDtoToTag(TagDto tagDto);
    public List<Tag> changeCertificateDtoToTagList(CertificateDto dto);


}
