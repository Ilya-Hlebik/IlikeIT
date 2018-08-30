package com.wgdetective.projectstartdemo.converter;

import com.wgdetective.projectstartdemo.dbo.LIfePositionDbo;
import com.wgdetective.projectstartdemo.dbo.PositionDbo;
import com.wgdetective.projectstartdemo.dto.LifePositionDto;
import com.wgdetective.projectstartdemo.dto.PositionDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class LifePositionConverter implements DtoConverter<LifePositionDto, LIfePositionDbo>{
    @Override
    public LifePositionDto convertToDto(final LIfePositionDbo dbo) {
        final LifePositionDto lifePositionDto = new LifePositionDto();
        BeanUtils.copyProperties(dbo,lifePositionDto);
        return lifePositionDto;
    }

    @Override
    public LIfePositionDbo convertToDbo(final LifePositionDto dto) {
        final LIfePositionDbo lIfePositionDbo = new LIfePositionDbo();
        BeanUtils.copyProperties(dto, lIfePositionDbo);
        return lIfePositionDbo;
    }
}
