package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.dbo.LifePositionDbo;
import com.idglebik.ilikeit.dto.LifePositionDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class LifePositionConverter implements DtoConverter<LifePositionDto, LifePositionDbo>{
    @Override
    public LifePositionDto convertToDto(final LifePositionDbo dbo) {
        final LifePositionDto lifePositionDto = new LifePositionDto();
        BeanUtils.copyProperties(dbo,lifePositionDto);
        return lifePositionDto;
    }

    @Override
    public LifePositionDbo convertToDbo(final LifePositionDto dto) {
        final LifePositionDbo lIfePositionDbo = new LifePositionDbo();
        BeanUtils.copyProperties(dto, lIfePositionDbo);
        return lIfePositionDbo;
    }
}
