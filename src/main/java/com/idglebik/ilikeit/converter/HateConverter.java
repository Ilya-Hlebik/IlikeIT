package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.dbo.HateDbo;
import com.idglebik.ilikeit.dto.HateDto;
import com.idglebik.ilikeit.enumerated.Hate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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


    public Set<HateDbo> convertToDbo(final Set<HateDto> dto) {
        if (dto != null) {
            return dto.stream().map(this::convertToDbo).collect(Collectors.toSet());
        } else {
            return null;
        }
    }
}
