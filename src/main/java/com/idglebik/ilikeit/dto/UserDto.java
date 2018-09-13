package com.idglebik.ilikeit.dto;

import com.idglebik.ilikeit.enumerated.Sex;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private Set<Sex> sexes;
    @EqualsAndHashCode.Exclude
    private Set<PositionDto> positions;
    @EqualsAndHashCode.Exclude
    public Set<StudyDto> studies;
    @EqualsAndHashCode.Exclude
    private Set<LangDto> languages;
    @EqualsAndHashCode.Exclude
    public Set<LikeDto> likes;
    @EqualsAndHashCode.Exclude
    private Set<HateDto> hates;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private LifePositionDto lifePosition;
}