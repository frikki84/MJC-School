package com.epam.esm.service.impl;

import com.epam.esm.service.Tag;
import com.epam.esm.service.TagDao;
import com.epam.esm.service.config.TestConfiguration;
import com.epam.esm.service.exception.NoSuchResourceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles("dev")
class TagDaoImplTest {
    public static final int LIST_SIZE_FIND_ALL_TAG_LIST = 6;
    public static final int ID_FIND_TAG_BY_ID = 3;
    public static final int ID_FIND_TAG_BY_ID_EXCEPTION = 1000;
    public static final String NAME_FIND_TAG_BY_NAME = "spa";
    public static final String NAME_FIND_TAG_BY_NAME_EXCEPTION = "disco";
    public static final Tag NEW_TAG_ADD_NEW_TAG = new Tag(7, "swimming");
    public static final int CERTIFICATE_ID_FIND_TAGS_BY_CERTIFICATE_ID= 36;

    @Autowired
    private TagDao tagDao;

    @Test
    void testFindAllTagList() {
        List<Tag> list = tagDao.findAllTagList();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(LIST_SIZE_FIND_ALL_TAG_LIST, list.size());
    }

    @Test
    void testFindTagById() {
        Assertions.assertNotNull(tagDao.findTag(ID_FIND_TAG_BY_ID));
    }

    @Test
    void testFindTagByIdException() {
        Throwable throwable = Assertions.assertThrows(NoSuchResourceException.class, () -> {
            tagDao.findTag(ID_FIND_TAG_BY_ID_EXCEPTION);
        });
        Assertions.assertEquals(NoSuchResourceException.class, throwable.getClass());
    }

    @Test
    void testFindTagByName() {
        Assertions.assertNotNull(tagDao.findTag(NAME_FIND_TAG_BY_NAME));
    }

    @Test
    void testFindTagByNameException() {
        Throwable throwable = Assertions.assertThrows(NoSuchResourceException.class, () -> {
            tagDao.findTag(NAME_FIND_TAG_BY_NAME_EXCEPTION);
        });
        Assertions.assertEquals(NoSuchResourceException.class, throwable.getClass());
    }

    @Test
    void testAddNewTag() {
        Tag tag = NEW_TAG_ADD_NEW_TAG;
        Assertions.assertNotNull(tag);
        Assertions.assertEquals(tag.getId(), NEW_TAG_ADD_NEW_TAG.getId());

    }


    @Test
    void testFindTagsByCertificateId() {
        List<Tag> tagList = tagDao.findTagsByCertificateId(CERTIFICATE_ID_FIND_TAGS_BY_CERTIFICATE_ID);
        Assertions.assertNotNull(tagList);
        Assertions.assertTrue(tagList.isEmpty());
    }


}