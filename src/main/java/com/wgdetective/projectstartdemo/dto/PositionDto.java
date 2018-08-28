package com.wgdetective.projectstartdemo.dto;

import com.wgdetective.projectstartdemo.dbo.UserDbo;
import com.wgdetective.projectstartdemo.enumerated.Position;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class PositionDto {
    private long id;
    private Position positionName;
}
