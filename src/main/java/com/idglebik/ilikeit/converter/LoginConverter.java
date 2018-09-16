package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.dbo.LoginDbo;
import com.idglebik.ilikeit.dto.LoginDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class LoginConverter implements DtoConverter<LoginDto, LoginDbo> {
    @Override
    public LoginDto convertToDto(final LoginDbo dbo) {
        final LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(dbo, loginDto);
        return loginDto;
    }

    @Override
    public LoginDbo convertToDbo(final LoginDto dto) {
        final LoginDbo loginDbo = new LoginDbo();
        BeanUtils.copyProperties(dto, loginDbo);
        return loginDbo;
    }
}
