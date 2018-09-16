package com.idglebik.ilikeit.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class LoginDto {
    @NotNull
    @Size(min = 4, max = 60)
    private String username;
    @NotNull
    @Size(min = 8, max = 60)
    private String password;
}
