package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.dbo.LifePositionDbo;
import com.idglebik.ilikeit.dto.LifePositionDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LifePositionConverterTest {

    private LifePositionConverter lifePositionConverter;

    @Test
    public void convertToDto() {
        final LifePositionDbo lifePositionDbo = MockData.lifePositionDbo();
        final LifePositionDto lifePositionDto = lifePositionConverter.convertToDto(lifePositionDbo);
        assertEquals(lifePositionDbo.getMainInPeople(), lifePositionDto.getMainInPeople());
        assertEquals(lifePositionDbo.getMainInLife(), lifePositionDto.getMainInLife());
        assertEquals(lifePositionDbo.getAligment(), lifePositionDto.getAligment());
    }

    @Test
    public void convertToDbo() {
        final LifePositionDto lifePositionDto = MockData.lifePositionDto();
        final LifePositionDbo lifePositionDbo = lifePositionConverter.convertToDbo(lifePositionDto);
        assertEquals(lifePositionDto.getMainInPeople(), lifePositionDbo.getMainInPeople());
        assertEquals(lifePositionDto.getMainInLife(), lifePositionDbo.getMainInLife());
        assertEquals(lifePositionDto.getAligment(), lifePositionDbo.getAligment());
    }

    @Before
    public void setLifePositionConverter() {
        lifePositionConverter = new LifePositionConverter();
    }
}
