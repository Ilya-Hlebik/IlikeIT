package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.dbo.LikeDbo;
import com.idglebik.ilikeit.dto.LikeDto;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;


public class DtoConverterTest {

    private LikeConverter likeConverter;

    @Test
    public void convertSetToDto() {
        final Set<LikeDbo> likeDbos = new HashSet<>(Collections.singletonList(MockData.likeDbo()));
        final Set<LikeDto> likeDtos = likeConverter.convertToDto(likeDbos);
        assertEquals(likeDbos.iterator().next().getLike(), likeDtos.iterator().next().getLike());
    }

    @Test
    public void convertSetToDbo() {
        final Set<LikeDto> likeDtos = new HashSet<>(Collections.singletonList(MockData.likeDto()));
        final Set<LikeDbo> likeDbos = likeConverter.convertToDbo(likeDtos);
        assertEquals(likeDtos.iterator().next().getLike(), likeDbos.iterator().next().getLike());
    }

    @Test
    public void convertListToDto() {
        final List<LikeDbo> likeDbos = new ArrayList<>(Collections.singletonList(MockData.likeDbo()));
        final List<LikeDto> likeDtos = likeConverter.convertToDto(likeDbos);
        assertEquals(likeDbos.iterator().next().getLike(), likeDtos.iterator().next().getLike());
    }

    public void convertListToDbo() {
        final List<LikeDto> likeDtos = new ArrayList<>(Collections.singletonList(MockData.likeDto()));
        final List<LikeDbo> likeDbos = likeConverter.convertToDbo(likeDtos);
        assertEquals(likeDtos.iterator().next().getLike(), likeDbos.iterator().next().getLike());
    }

    @Before
    public void setLikeConverter() {
        likeConverter = new LikeConverter();
    }
}
