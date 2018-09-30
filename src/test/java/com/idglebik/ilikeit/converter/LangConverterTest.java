package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.dbo.LangDbo;
import com.idglebik.ilikeit.dto.LangDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LangConverterTest {

    private LangConverter langConverter;

    @Test
    public void convertToDto() {
        final LangDbo langDbo = MockData.langDbo();
        final LangDto langDto = langConverter.convertToDto(langDbo);
        assertEquals(langDbo.getLanguage(),langDto.getLanguage());
    }

    @Test
    public void convertToDbo() {
        final LangDto langDto = MockData.langDto();
        final LangDbo langDbo = langConverter.convertToDbo(langDto);
        assertEquals(langDto.getLanguage(),langDbo.getLanguage());
    }

    @Before
    public void setLangConverter() {
        langConverter = new LangConverter();
    }
}
