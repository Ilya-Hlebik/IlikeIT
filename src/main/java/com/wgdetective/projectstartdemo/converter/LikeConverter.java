package com.wgdetective.projectstartdemo.converter;

import com.wgdetective.projectstartdemo.dbo.LikeDbo;
import com.wgdetective.projectstartdemo.dto.LikeDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class LikeConverter implements DtoConverter<LikeDto, LikeDbo> {
    @Override
    public LikeDto convertToDto(final LikeDbo dbo) {
        final LikeDto likeDto = new LikeDto();
        BeanUtils.copyProperties(dbo, likeDto);
        return likeDto;
    }

    @Override
    public LikeDbo convertToDbo(final LikeDto dto) {
        final LikeDbo likeDbo = new LikeDbo();
        BeanUtils.copyProperties(dto, likeDbo);
        return likeDbo;
    }
}
