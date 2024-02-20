package com.sparta.managemyschedule.controller;

import com.sparta.managemyschedule.auth.security.UserDetailsImpl;
import com.sparta.managemyschedule.dto.requestDto.SignupRequestDto;
import com.sparta.managemyschedule.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public void signup(@Valid @RequestBody SignupRequestDto requestDto){
        userService.signup(requestDto);
    }

    @GetMapping("/user-info")
    public String getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUser().getUsername();
        return username;
    }
}
