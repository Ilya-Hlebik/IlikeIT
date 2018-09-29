package com.idglebik.ilikeit.dto;

import com.idglebik.ilikeit.enumerated.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto {
    private Position positionName;
}
