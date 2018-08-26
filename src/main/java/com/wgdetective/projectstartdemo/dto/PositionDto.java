package com.wgdetective.projectstartdemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class PositionDto {
    private long id;
    private String positionName;
    private Set<UserDto> userDtos;
}
