package com.idglebik.ilikeit.controller;

import com.idglebik.ilikeit.config.Response;
import com.idglebik.ilikeit.dto.ChatMessageDto;
import com.idglebik.ilikeit.dto.ChatMessageResponseDto;
import com.idglebik.ilikeit.service.ChatMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("RestController")
@AllArgsConstructor
@RequestMapping("/message")
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    @ApiOperation("send msg")
    @PostMapping
    @ResponseBody
    public ResponseEntity<Response<ChatMessageResponseDto>> sendMessage(@RequestBody ChatMessageDto chatMessage
            , Authentication auth) {
        return chatMessageService.sendMessage(chatMessage, auth);
    }

    @ApiOperation("get msg")
    @GetMapping
    @ResponseBody
    public ResponseEntity<Response<List<ChatMessageResponseDto>>> getMessages(@RequestParam long sender
            , @RequestParam long recipient, Authentication auth) {
       return chatMessageService.getMessages(sender, recipient, auth);
    }
}
