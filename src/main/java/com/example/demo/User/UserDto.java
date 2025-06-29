package com.example.demo.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String role;
}
