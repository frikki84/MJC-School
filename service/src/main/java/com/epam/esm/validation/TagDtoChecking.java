package com.epam.esm.validation;

import com.epam.esm.dto.TagDto;

public class TagDtoChecking {
    public static final boolean DEFAULT_RESULT = true;
    public static final boolean RESULT_AFTER_TERMS = false;


    public static boolean checkTAgDto (TagDto tagDto) {
        boolean result = DEFAULT_RESULT;
        if (tagDto.getNameTag() == null || tagDto.getNameTag().isEmpty() || tagDto.getNameTag().isBlank()) {
            result = RESULT_AFTER_TERMS;
        }
        return result;
    }
}
