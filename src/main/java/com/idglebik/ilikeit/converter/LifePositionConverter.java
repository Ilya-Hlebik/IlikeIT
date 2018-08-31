package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.dbo.LIfePositionDbo;
import com.idglebik.ilikeit.dto.LifePositionDto;
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
