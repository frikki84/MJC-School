package com.epam.esm;

import com.epam.esm.dto.TagDto;
import com.epam.esm.exception.NoSuchResourceException;
import com.epam.esm.exception.TagValidationException;
import com.epam.esm.exception.TagAlreadyExistsException;
import com.epam.esm.util.CustomErrorCode;
import com.epam.esm.validation.TagDtoChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tags")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping()
    public List<Tag> findAllCertificates() {
        List<Tag> fullTagList = tagService.findAllTagList();
        return fullTagList;
    }

    @GetMapping("/{id}")
    public Tag findTag(@PathVariable("id") int id) {
        Tag tag = tagService.findTag(id);

        if (tag == null) {
            throw new NoSuchResourceException(CustomErrorCode.TAG);
        }
        return tag;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Tag createNewTag(TagDto tag) {
        boolean checkTag = TagDtoChecking.checkTAgDto(tag);
        if (!checkTag) {
            throw new TagValidationException(CustomErrorCode.TAG);
        }
        Tag createdTag = tagService.addNewTag(tag);
        if (createdTag == null) {
            throw new TagAlreadyExistsException(CustomErrorCode.TAG);
        }
        return createdTag;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Integer deleteCertificate(@PathVariable("id") int id) {
        Integer fields = tagService.deleteTag(id);
        if (fields == null | fields == 0) {
            throw new NoSuchResourceException(CustomErrorCode.TAG);
        }
        return fields;
    }


}
