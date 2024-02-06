package com.sparta.managemyschedule.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {

    @NotBlank
    @Pattern(message = "최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)를 이용해서 생성해주세요.",
    regexp ="^[a-z0-9]{4,10}")
    private String username;
    @NotBlank
    @Pattern(message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)를 이용해서 생성해주세요.",
    regexp = "^[a-zA-Z0-9]{8,15}")
    private String password;
    @Email
    @NotBlank
    private String email;
}
