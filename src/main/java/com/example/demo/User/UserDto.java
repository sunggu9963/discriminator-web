package com.example.demo.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    @Size(min = 4, max = 12)
    @NotBlank(message = "ID를 입력해주세요.")
    private String username;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotBlank(message = "비밀번호 확인은 필수항목입니다.")
    private String passwordcheck;
    @NotBlank(message = "이메일은 입력해주세요.")
    @Email
    private String email;
    //private String role;
}
