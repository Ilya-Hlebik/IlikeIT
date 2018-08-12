package com.wgdetective.projectstartdemo.converter;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.wgdetective.projectstartdemo.dbo.UserDbo;
import com.wgdetective.projectstartdemo.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserConverter implements DtoConverter<UserDto, UserDbo>{
    @Override
    public UserDto convertToDto(final UserDbo dbo) {
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(dbo,userDto);
        return userDto;
    }

    @Override
    public UserDbo convertToDbo(final UserDto dto) {
        final UserDbo userDbo = new UserDbo();
        BeanUtils.copyProperties(dto, userDbo);
        return userDbo;
    }
}
