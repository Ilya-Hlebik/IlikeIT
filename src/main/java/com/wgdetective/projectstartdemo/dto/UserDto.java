package com.wgdetective.projectstartdemo.dto;

import com.wgdetective.projectstartdemo.enumerated.Sex;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private int age;
    private String city;
    private String country;
    private String address;
    private String generalInfo;
    private String otherInfo;
    @EqualsAndHashCode.Exclude
    private Set<PositionDto> position;
    @EqualsAndHashCode.Exclude
    private Set<Sex> sex;
    @EqualsAndHashCode.Exclude
    public Set<StudyDto> studys;
    @EqualsAndHashCode.Exclude
    private Set<LangDto> language;
    @EqualsAndHashCode.Exclude
    public Set<LikeDto> like;
    @EqualsAndHashCode.Exclude
    private Set<HateDto> hate;
}