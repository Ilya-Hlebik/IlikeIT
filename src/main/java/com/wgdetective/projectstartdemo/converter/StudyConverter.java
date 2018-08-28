package com.wgdetective.projectstartdemo.converter;

import com.wgdetective.projectstartdemo.dbo.PositionDbo;
import com.wgdetective.projectstartdemo.dbo.StudyDbo;
import com.wgdetective.projectstartdemo.dto.PositionDto;
import com.wgdetective.projectstartdemo.dto.StudyDto;
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
