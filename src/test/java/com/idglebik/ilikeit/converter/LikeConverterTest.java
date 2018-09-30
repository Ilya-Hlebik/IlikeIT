package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.dbo.LikeDbo;
import com.idglebik.ilikeit.dto.LikeDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LikeConverterTest {

    private LikeConverter likeConverter;

    @Test
    public void convertToDto() {
        final LikeDbo likeDbo = MockData.likeDbo();
        final LikeDto likeDto = likeConverter.convertToDto(likeDbo);
        assertEquals(likeDbo.getLike(),likeDto.getLike());
    }

    @Test
    public void convertToDbo() {
        final LikeDto likeDto = MockData.likeDto();
        final LikeDbo likeDbo = likeConverter.convertToDbo(likeDto);
        assertEquals(likeDto.getLike(), likeDbo.getLike());
    }

    @Before
    public void setLikeConverter() {
        likeConverter = new LikeConverter();
    }
}
