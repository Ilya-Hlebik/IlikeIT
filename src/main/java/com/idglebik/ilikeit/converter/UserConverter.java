package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.dbo.UserDbo;
import com.idglebik.ilikeit.dto.UserDto;
import com.idglebik.ilikeit.enumerated.Position;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserConverter implements DtoConverter<UserDto, UserDbo>{

    @Autowired
    private  LifePositionConverter lifePositionConverter;

    @Autowired
    private  FriendConverter friendConverter;

    @Autowired
    private  StudyConverter studyConverter;

    @Autowired
    private  PositionConverter positionConverter;

    @Autowired
    private  LikeConverter likeConverter;

    @Autowired
    private  HateConverter hateConverter;

    @Autowired
    private  LangConverter langConverter;

    @Override
    public UserDto convertToDto(final UserDbo dbo) {
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(dbo,userDto);
        userDto.setLifePosition(lifePositionConverter.convertToDto(dbo.getLIfePosition()));
        userDto.setStudies(studyConverter.convertToDto(dbo.getStudies()));
        userDto.setFriendDtos(friendConverter.convertToDto(dbo.getFriendDbos()));
        userDto.setPositions(positionConverter.convertToDto(dbo.getPositions()));
        userDto.setLikes(likeConverter.convertToDto(dbo.getLikes()));
        userDto.setHates(hateConverter.convertToDto(dbo.getHates()));
        userDto.setLanguages(langConverter.convertToDto(dbo.getLanguages()));
        return userDto;
    }

    @Override
    public UserDbo convertToDbo(final UserDto dto) {
        final UserDbo userDbo = new UserDbo();
        BeanUtils.copyProperties(dto, userDbo);
        userDbo.setLIfePosition(lifePositionConverter.convertToDbo(dto.getLifePosition()));
        userDbo.setStudies(studyConverter.convertToDbo(dto.getStudies()));
        userDbo.setFriendDbos(friendConverter.convertToDbo(dto.getFriendDtos()));
        userDbo.setPositions(positionConverter.convertToDbo(dto.getPositions()));
        userDbo.setLikes(likeConverter.convertToDbo(dto.getLikes()));
        userDbo.setHates(hateConverter.convertToDbo(dto.getHates()));
        userDbo.setLanguages(langConverter.convertToDbo(dto.getLanguages()));
        return userDbo;
    }
}
