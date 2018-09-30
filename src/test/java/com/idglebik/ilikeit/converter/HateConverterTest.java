package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.dbo.HateDbo;
import com.idglebik.ilikeit.dto.HateDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HateConverterTest {

    private HateConverter hateConverter;

    @Test
    public void convertToDto() {
        final HateDbo hateDbo = MockData.hateDbo();
        final HateDto hateDto = hateConverter.convertToDto(hateDbo);
        assertEquals(hateDbo.getHate(), hateDto.getHate());
    }

    @Test
    public void convertToDbo() {
        final HateDto hateDto = MockData.hateDto();
        final HateDbo hateDbo = hateConverter.convertToDbo(hateDto);
        assertEquals(hateDto.getHate(), hateDbo.getHate());
    }

    @Before
    public void setHateConverter() {
        hateConverter = new HateConverter();
    }
}
