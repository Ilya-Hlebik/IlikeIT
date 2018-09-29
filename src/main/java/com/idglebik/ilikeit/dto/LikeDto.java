package com.idglebik.ilikeit.dto;

import com.idglebik.ilikeit.enumerated.Like;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeDto {
    private Like like;
}
