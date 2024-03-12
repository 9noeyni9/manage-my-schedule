package com.sparta.managemyschedule.service;

import com.sparta.managemyschedule.dto.requestDto.SignupRequestDto;

public interface UserService {

    void signup(SignupRequestDto requestDto);
}
