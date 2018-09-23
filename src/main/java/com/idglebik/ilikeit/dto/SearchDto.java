package com.idglebik.ilikeit.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    UserDto userDto;
    Long userId;
}
