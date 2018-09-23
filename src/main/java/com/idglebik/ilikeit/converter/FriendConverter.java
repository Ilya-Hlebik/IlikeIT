package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.dbo.FriendDbo;
import com.idglebik.ilikeit.dbo.StudyDbo;
import com.idglebik.ilikeit.dto.FriendDto;
import com.idglebik.ilikeit.dto.StudyDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class FriendConverter implements DtoConverter<FriendDto, FriendDbo>{
    @Override
    public FriendDto convertToDto(final FriendDbo dbo) {
        final FriendDto friendDto = new FriendDto();
        BeanUtils.copyProperties(dbo,friendDto);
        return friendDto;
    }

    @Override
    public FriendDbo convertToDbo(final FriendDto dto) {
        final FriendDbo friendDbo = new FriendDbo();
        BeanUtils.copyProperties(dto, friendDbo);
        return friendDbo;
    }
}
