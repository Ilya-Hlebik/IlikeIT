package com.idglebik.ilikeit.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatMessageDto {
    private String content;
    private long sender;
    private long recipient;
}
