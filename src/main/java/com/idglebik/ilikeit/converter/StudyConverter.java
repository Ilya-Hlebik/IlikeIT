package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.dbo.StudyDbo;
import com.idglebik.ilikeit.dto.StudyDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class StudyConverter implements DtoConverter<StudyDto, StudyDbo>{
    @Override
    public StudyDto convertToDto(final StudyDbo dbo) {
        final StudyDto studyDto = new StudyDto();
        BeanUtils.copyProperties(dbo,studyDto);
        return studyDto;
    }

    @Override
    public StudyDbo convertToDbo(final StudyDto dto) {
        final StudyDbo studyDbo = new StudyDbo();
        BeanUtils.copyProperties(dto, studyDbo);
        return studyDbo;
    }
}
