package com.idglebik.ilikeit.service;

import com.idglebik.ilikeit.config.Response;
import com.idglebik.ilikeit.converter.ChatMessageConverter;
import com.idglebik.ilikeit.dbo.ChatMessageDbo;
import com.idglebik.ilikeit.dbo.LoginDbo;
import com.idglebik.ilikeit.dbo.UserDbo;
import com.idglebik.ilikeit.dto.ChatMessageDto;
import com.idglebik.ilikeit.dto.ChatMessageResponseDto;
import com.idglebik.ilikeit.enumerated.Role;
import com.idglebik.ilikeit.repository.ChatMessageRepository;
import com.idglebik.ilikeit.repository.LoginRepository;
import com.idglebik.ilikeit.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatMessageService {
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageConverter chatMessageConverter;

    public ResponseEntity<Response<ChatMessageResponseDto>> sendMessage(ChatMessageDto chatMessage, Authentication auth) {

        LoginDbo loginDbo = loginRepository.findByUsername(auth.getName());
        final Optional<UserDbo> userDbo = userRepository.findById(chatMessage.getSender());
        final List<UserDbo> userDboList = userRepository.findByLoginDboUsername(auth.getName());
        Boolean isRightUser = userDboList.stream().anyMatch(e -> e.getId() == chatMessage.getSender()) || loginDbo.getRoles().contains(Role.ADMIN);
        final Optional<UserDbo> friendUserDbo = userRepository.findById(chatMessage.getRecipient());
        if (!userDbo.isPresent() || !friendUserDbo.isPresent() || !isRightUser) {
            return new ResponseEntity(Response.error("can't add to friend List"), HttpStatus.BAD_REQUEST);
        }

        ChatMessageResponseDto responseDto = new ChatMessageResponseDto();
        BeanUtils.copyProperties(chatMessage,responseDto);
        responseDto.setDate(new Date());
        ChatMessageDbo messageDbo = chatMessageConverter.convertToDbo(chatMessage);
        messageDbo.setDate(responseDto.getDate());
        messageDbo.setHistoryId(chatMessage.getSender() + "+" + chatMessage.getRecipient());
        chatMessageRepository.save(messageDbo);
        return ResponseEntity.ok(Response.success(responseDto));
    }

    public ResponseEntity<Response<List<ChatMessageResponseDto>>> getMessages(long sender, long recipient, Authentication auth) {
        LoginDbo loginDbo = loginRepository.findByUsername(auth.getName());
        final Optional<UserDbo> userDbo = userRepository.findById(sender);
        final List<UserDbo> userDboList = userRepository.findByLoginDboUsername(auth.getName());
        Boolean isRightUser = userDboList.stream().anyMatch(e -> e.getId() == sender) || loginDbo.getRoles().contains(Role.ADMIN);
        if (!userDbo.isPresent()|| !isRightUser) {
            return new ResponseEntity(Response.error("can't send message"), HttpStatus.BAD_REQUEST);
        }
        List<ChatMessageDbo> messages = chatMessageRepository.findAllBySenderAndRecipientOrRecipientAndSenderOrderByDateDesc
                (sender, recipient, sender, recipient);
        List<ChatMessageResponseDto> responseDtos = new ArrayList<>();

        messages.forEach(message -> responseDtos.add(new ChatMessageResponseDto(message.getContent(), message.getDate(), message.getSender())));
        return ResponseEntity.ok(Response.success(responseDtos));
    }
}
