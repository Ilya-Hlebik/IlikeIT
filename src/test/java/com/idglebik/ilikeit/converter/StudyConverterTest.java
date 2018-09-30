package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.dbo.StudyDbo;
import com.idglebik.ilikeit.dto.StudyDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StudyConverterTest {
    private StudyConverter studyConverter;

    @Test
    public void convertToDto() {
        final StudyDbo studyDbo = MockData.studyDbo();
        final StudyDto studyDto = studyConverter.convertToDto(studyDbo);
        assertEquals(studyDbo.getInstitution(), studyDto.getInstitution());
    }

    @Test
    public void convertToDbo() {
        final StudyDto studyDto = MockData.studyDto();
        final StudyDbo studyDbo = studyConverter.convertToDbo(studyDto);
        assertEquals(studyDto.getInstitution(), studyDbo.getInstitution());

    }

    @Before
    public void setStudyConverter() {
        studyConverter = new StudyConverter();
    }
}
