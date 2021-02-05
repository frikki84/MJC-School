package com.epam.esm;

import com.epam.esm.dto.TagDto;
import com.epam.esm.exception.TagValidationException;
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

    @GetMapping("/{name}")
    public Tag findTag(@PathVariable("name") String name) {
        Tag tag = tagService.findTag(name);
        return tag;
    }

    @PostMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Tag createNewTag(@RequestBody TagDto tag) {
        boolean checkTag = TagDtoChecking.checkTAgDto(tag);
        if (!checkTag) {
            throw new TagValidationException(CustomErrorCode.TAG);
        }
        Tag createdTag = tagService.addNewTag(tag);
        return createdTag;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Integer deleteCertificate(@PathVariable("id") int id) {
        Integer fields = tagService.deleteTag(id);
        return  fields;
    }


}
