package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.dbo.FriendDbo;
import com.idglebik.ilikeit.dto.FriendDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FriendConverterTest {

    private FriendConverter friendConverter;

    @Test
    public void convertToDto() {
        final FriendDbo friendDbo = MockData.friendDbo();
        final FriendDto friendDto = friendConverter.convertToDto(friendDbo);
        assertEquals(friendDbo.getFriendId(), friendDto.getFriendId());
    }

    @Test
    public void convertToDbo() {
        final FriendDto friendDto = MockData.friendDto();
        final FriendDbo friendDbo = friendConverter.convertToDbo(friendDto);
        assertEquals(friendDto.getFriendId(), friendDbo.getFriendId());
    }

    @Before
    public void setChatMessageConverter() {
        friendConverter = new FriendConverter();
    }
}
