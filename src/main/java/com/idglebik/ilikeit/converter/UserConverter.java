package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.dbo.UserDbo;
import com.idglebik.ilikeit.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserConverter implements DtoConverter<UserDto, UserDbo>{

    @Autowired
    LifePositionConverter lifePositionConverter;

    @Autowired
    FriendConverter friendConverter;

    @Override
    public UserDto convertToDto(final UserDbo dbo) {
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(dbo,userDto);
        userDto.setLifePosition(lifePositionConverter.convertToDto(dbo.getLIfePosition()));
        userDto.setFriendDtos(friendConverter.convertToDto(dbo.getFriendDbos()));
        return userDto;
    }

    @Override
    public UserDbo convertToDbo(final UserDto dto) {
        final UserDbo userDbo = new UserDbo();
        BeanUtils.copyProperties(dto, userDbo);
        return userDbo;
    }
}
