package com.idglebik.ilikeit.dto;

import com.idglebik.ilikeit.enumerated.Hate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HateDto {
    private Hate hate;
}
