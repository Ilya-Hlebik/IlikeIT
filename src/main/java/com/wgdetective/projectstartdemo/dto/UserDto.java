package com.wgdetective.projectstartdemo.dto;

import com.wgdetective.projectstartdemo.dbo.Sex;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String position;
    private Set<Sex> sex;
}
