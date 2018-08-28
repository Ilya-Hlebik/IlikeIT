package com.wgdetective.projectstartdemo.dto;

import com.wgdetective.projectstartdemo.enumerated.Sex;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class StudyDto {
    private String institution;
    private long userId;
}

