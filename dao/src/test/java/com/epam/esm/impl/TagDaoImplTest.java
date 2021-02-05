package com.epam.esm.impl;

import com.epam.esm.Tag;
import com.epam.esm.TagDao;
import com.epam.esm.exception.NoSuchResourceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import java.util.List;


class TagDaoImplTest {

    private EmbeddedDatabase embeddedDatabase;
    private JdbcTemplate jdbcTemplate;
    private TagDao tagDao;

    @BeforeEach
    public void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("mydb.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        tagDao = new TagDaoImpl(jdbcTemplate);
    }


    @Test
    void testFindAllTagList() {
        System.out.println(tagDao);
        Assertions.assertNotNull(tagDao.findAllTagList());
        Assertions.assertEquals(6, tagDao.findAllTagList().size());
    }

    @Test
    void testFindTagById() {
        Assertions.assertNotNull(tagDao.findTag(3));
    }

    @Test
    void testFindTagByIdException() {
        long id = 1000;
        Throwable throwable = Assertions.assertThrows(NoSuchResourceException.class, () -> {
            tagDao.findTag(id);
        });
        Assertions.assertEquals(NoSuchResourceException.class, throwable.getClass());
    }

    @Test
    void testFindTagByName() {
        Assertions.assertNotNull(tagDao.findTag("spa"));
    }

    @Test
    void testFindTagByNameException() {
        String tagName = "lalala";
        Throwable throwable = Assertions.assertThrows(NoSuchResourceException.class, () -> {
            tagDao.findTag(tagName);
        });
        Assertions.assertEquals(NoSuchResourceException.class, throwable.getClass());
    }

    @Test
    void testAddNewTag() {
        Tag tag = tagDao.addNewTag(new Tag(7, "swimming"));
        Assertions.assertNotNull(tag);
        Assertions.assertNotNull(tag.getId());
        Assertions.assertEquals(tag, new Tag(7, "swimming"));

    }


    @Test
    void testFindTagsByCertificateId() {
        long certIdWrong = 52;
        List<Tag> tagList = tagDao.findTagsByCertificateId(certIdWrong);
        Assertions.assertNotNull(tagList);
        Assertions.assertTrue(tagList.isEmpty());
    }

    @AfterEach
    public void endTest() {
        embeddedDatabase.shutdown();
    }

}