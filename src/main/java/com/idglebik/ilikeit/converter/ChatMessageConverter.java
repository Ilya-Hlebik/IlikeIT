package com.idglebik.ilikeit.converter;

import com.idglebik.ilikeit.dbo.ChatMessageDbo;
import com.idglebik.ilikeit.dbo.FriendDbo;
import com.idglebik.ilikeit.dto.ChatMessageDto;
import com.idglebik.ilikeit.dto.FriendDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageConverter implements DtoConverter<ChatMessageDto, ChatMessageDbo> {
    @Override
    public ChatMessageDto convertToDto(final ChatMessageDbo dbo) {
        final ChatMessageDto chatMessageDto = new ChatMessageDto();
        BeanUtils.copyProperties(dbo, chatMessageDto);
        return chatMessageDto;
    }

    @Override
    public ChatMessageDbo convertToDbo(final ChatMessageDto dto) {
        final ChatMessageDbo chatMessageDbo = new ChatMessageDbo();
        BeanUtils.copyProperties(dto, chatMessageDbo);
        return chatMessageDbo;
    }
}
