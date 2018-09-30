package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.dbo.ChatMessageDbo;
import com.idglebik.ilikeit.dto.ChatMessageDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ChatMessageConverterTest {

    private ChatMessageConverter chatMessageConverter;

    @Test
    public void convertToDto() {
        final ChatMessageDbo chatMessageDbo = MockData.chatMessageDbo();
        final ChatMessageDto chatMessageDto = chatMessageConverter.convertToDto(chatMessageDbo);
        assertEquals(chatMessageDbo.getContent(), chatMessageDto.getContent());
        assertEquals(chatMessageDbo.getSender(), chatMessageDto.getSender());
        assertEquals(chatMessageDbo.getRecipient(), chatMessageDto.getRecipient());
    }

    @Test
    public void convertToDbo() {
        final ChatMessageDto chatMessageDto = MockData.chatMessageDto();
        final ChatMessageDbo chatMessageDbo = chatMessageConverter.convertToDbo(chatMessageDto);
        assertEquals(chatMessageDto.getContent(), chatMessageDbo.getContent());
        assertEquals(chatMessageDto.getSender(), chatMessageDbo.getSender());
        assertEquals(chatMessageDto.getRecipient(), chatMessageDbo.getRecipient());
    }


    @Before
    public void setChatMessageConverter() {
        chatMessageConverter = new ChatMessageConverter();
    }
}
