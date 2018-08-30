package com.wgdetective.projectstartdemo.dto;

import com.wgdetective.projectstartdemo.enumerated.Aligment;
import com.wgdetective.projectstartdemo.enumerated.MainInLife;
import com.wgdetective.projectstartdemo.enumerated.MainInPeople;
import com.wgdetective.projectstartdemo.enumerated.Position;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LifePositionDto {
    private Aligment aligment;
    private MainInLife mainInLife;
    private MainInPeople mainInPeople;
}
