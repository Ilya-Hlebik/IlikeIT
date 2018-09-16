package com.idglebik.ilikeit.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idglebik.ilikeit.enumerated.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
public class LoginDto {
    @NotNull
    @Size(min = 4, max = 60)
    private String username;
    @NotNull
    @Size(min = 8, max = 60)
    private String password;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<Role> roles;
}
