package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.dbo.PositionDbo;
import com.idglebik.ilikeit.dto.PositionDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PositionConverterTest {

    private PositionConverter positionConverter;

    @Test
    public void convertToDto() {
        final PositionDbo positionDbo = MockData.positionDbo();
        final PositionDto positionDto = positionConverter.convertToDto(positionDbo);
        assertEquals(positionDbo.getPositionName(), positionDto.getPositionName());
    }

    @Test
    public void convertToDbo() {
        final PositionDto positionDto = MockData.positionDto();
        final PositionDbo positionDbo = positionConverter.convertToDbo(positionDto);
        assertEquals(positionDto.getPositionName(), positionDbo.getPositionName());
    }

    @Before
    public void setPositionConverter() {
        positionConverter = new PositionConverter();
    }
}
