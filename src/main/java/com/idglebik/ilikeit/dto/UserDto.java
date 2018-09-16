package com.idglebik.ilikeit.dto;

import com.idglebik.ilikeit.enumerated.Sex;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Positive
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