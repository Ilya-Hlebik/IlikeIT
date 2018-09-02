package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.dbo.LangDbo;
import com.idglebik.ilikeit.dto.LangDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LangConverter implements DtoConverter<LangDto, LangDbo> {
    @Override
    public LangDto convertToDto(final LangDbo dbo) {
        final LangDto langDto = new LangDto();
        BeanUtils.copyProperties(dbo, langDto);
        return langDto;
    }

    @Override
    public LangDbo convertToDbo(final LangDto dto) {
        final LangDbo langDbo = new LangDbo();
        BeanUtils.copyProperties(dto, langDbo);
        return langDbo;
    }
}

