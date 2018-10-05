package com.idglebik.ilikeit.service;

import com.idglebik.ilikeit.MockData;
import com.idglebik.ilikeit.config.Response;
import com.idglebik.ilikeit.converter.ChatMessageConverter;
import com.idglebik.ilikeit.dbo.ChatMessageDbo;
import com.idglebik.ilikeit.dbo.LoginDbo;
import com.idglebik.ilikeit.dbo.UserDbo;
import com.idglebik.ilikeit.dto.ChatMessageDto;
import com.idglebik.ilikeit.dto.ChatMessageResponseDto;
import com.idglebik.ilikeit.repository.ChatMessageRepository;
import com.idglebik.ilikeit.repository.LoginRepository;
import com.idglebik.ilikeit.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ChatMessageServiceTest {

    @Autowired
    ChatMessageService chatMessageService;
    @MockBean
    LoginRepository loginRepository;
    @MockBean
    UserRepository userRepository;
    @MockBean
    ChatMessageRepository chatMessageRepository;
    @Autowired
    ChatMessageConverter chatMessageConverter;


    @Test
    public void sendMessage() {
        final ChatMessageDto chatMessageDto = MockData.chatMessageDto();
        final LoginDbo loginDbo = MockData.loginDboAdmin();
        final Optional<UserDbo> userDbo = Optional.of(MockData.userDbo());
        final Optional<UserDbo> friendDbo = Optional.of(MockData.userDbo());
        final List<UserDbo> findAllResult = Arrays.asList(userDbo.get(), userDbo.get());
        friendDbo.get().setId(2);

        doReturn(loginDbo).when(loginRepository).findByUsername(MockData.getAuthentication().getName());
        doReturn(userDbo).when(userRepository).findById(chatMessageDto.getSender());
        doReturn(friendDbo).when(userRepository).findById(chatMessageDto.getRecipient());
        doReturn(findAllResult).when(userRepository).findByLoginDboUsername(MockData.getAuthentication().getName());
        doReturn(chatMessageConverter.convertToDbo(chatMessageDto)).when(chatMessageRepository).save(any(ChatMessageDbo.class));

        ResponseEntity<Response<ChatMessageResponseDto>> sendMessageResponse = chatMessageService.sendMessage(chatMessageDto, MockData.getAuthentication());

        assertNotNull(sendMessageResponse.getBody().getData());
        assertEquals(sendMessageResponse.getStatusCode(), HttpStatus.OK);
        assertEquals(sendMessageResponse.getBody().getData().getContent(), chatMessageDto.getContent());
        verify(loginRepository, times(1)).findByUsername(MockData.getAuthentication().getName());
        verify(userRepository, times(1)).findById(chatMessageDto.getSender());
        verify(userRepository, times(1)).findById(chatMessageDto.getRecipient());
        verify(userRepository, times(1)).findByLoginDboUsername(MockData.getAuthentication().getName());
        verify(chatMessageRepository, times(1)).save(any(ChatMessageDbo.class));

    }

    @Test
    public void getMessages() {
        final ChatMessageDto chatMessageDto = MockData.chatMessageDto();
        final Optional<UserDbo> userDbo = Optional.of(MockData.userDbo());
        final List<UserDbo> findAllResult = Arrays.asList(userDbo.get(), userDbo.get());
        final LoginDbo loginDbo = MockData.loginDboAdmin();
        final List<ChatMessageDbo> messages = Arrays.asList(MockData.chatMessageDbo(), MockData.chatMessageDbo());

        doReturn(loginDbo).when(loginRepository).findByUsername(MockData.getAuthentication().getName());
        doReturn(userDbo).when(userRepository).findById(chatMessageDto.getSender());
        doReturn(findAllResult).when(userRepository).findByLoginDboUsername(MockData.getAuthentication().getName());
        doReturn(messages).when(chatMessageRepository).findAllBySenderAndRecipientOrRecipientAndSenderOrderByDateDesc(
                chatMessageDto.getSender(), chatMessageDto.getRecipient(), chatMessageDto.getSender(), chatMessageDto.getRecipient());


        chatMessageService.getMessages(chatMessageDto.getSender(), chatMessageDto.getRecipient(), MockData.getAuthentication());

        verify(userRepository, times(1)).findById(chatMessageDto.getSender());
        verify(loginRepository, times(1)).findByUsername(MockData.getAuthentication().getName());
        verify(userRepository, times(1)).findByLoginDboUsername(MockData.getAuthentication().getName());
        verify(chatMessageRepository, times(1)).findAllBySenderAndRecipientOrRecipientAndSenderOrderByDateDesc(
                chatMessageDto.getSender(), chatMessageDto.getRecipient(), chatMessageDto.getSender(), chatMessageDto.getRecipient());
    }

    @TestConfiguration
    public static class ChatMessageServiceTestConfiguration {

        @Bean
        public ChatMessageService chatMessageService(final UserRepository userRepository,
                                                     final LoginRepository loginRepository,
                                                     final ChatMessageRepository chatMessageRepository,
                                                     final ChatMessageConverter chatMessageConverter) {
            return new ChatMessageService(userRepository, loginRepository, chatMessageRepository, chatMessageConverter);
        }

        @Bean
        public ChatMessageConverter chatMessageConverter() {
            return new ChatMessageConverter();
        }
    }
}