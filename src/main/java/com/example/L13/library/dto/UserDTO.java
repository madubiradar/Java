package com.example.L13.library.dto;


import com.example.L13.library.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String address;

    @NotNull
    private Long phoneNumber;

    public User toUser(){
        return User.builder()
                        .name(name)
                                .email(email)
                                        .address(address)
                                                .phoneNumber(phoneNumber)
                .build();
    }
}
