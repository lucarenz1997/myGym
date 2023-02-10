package com.gymservice.web.userAPI;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateUserDto {

    @Size(min = 4, max = 20, message = "Must be between 4 and 20 and can not consist of special characters")
    @Pattern(regexp = "^[a-zA-Z0-9_.-]*$")
    @Null(groups = CreateUserDto.class)
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must be at least 8 characters long, contain at least 1 lowercase, 1 uppercase, 1 number and 1 special character")
    @Null(groups = CreateUserDto.class)
    private String password;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @Null(groups = CreateUserDto.class)
    private String email;
}
