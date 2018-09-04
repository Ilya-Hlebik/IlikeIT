package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.dbo.HateDbo;
import com.idglebik.ilikeit.dto.HateDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class HateConverter implements DtoConverter<HateDto, HateDbo> {
    @Override
    public HateDto convertToDto(final HateDbo dbo) {
        final HateDto hateDto = new HateDto();
        BeanUtils.copyProperties(dbo, hateDto);
        return hateDto;
    }

    @Override
    public HateDbo convertToDbo(final HateDto dto) {
        final HateDbo hateDbo = new HateDbo();
        BeanUtils.copyProperties(dto, hateDbo);
        return hateDbo;
    }
}
