package com.idglebik.ilikeit.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageResponseDto {
    private String content;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Date date;
    private long sender;
}