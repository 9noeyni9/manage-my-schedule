package com.sparta.managemyschedule.controller;

import com.sparta.managemyschedule.auth.security.UserDetailsImpl;
import com.sparta.managemyschedule.dto.requestDto.SignupRequestDto;
import com.sparta.managemyschedule.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public String signup(@Valid SignupRequestDto requestDto,
                                       BindingResult bindingResult){
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size()>0){
            for(FieldError fieldError : bindingResult.getFieldErrors()){
                log.error(fieldError.getField() + "필드 : " + fieldError.getDefaultMessage());
            }
            return "redirect:/api/user/signup";
        }
        userService.signup(requestDto);
        return "로그인!";
    }

    @GetMapping("/user-info")
    @ResponseBody
    public String getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUser().getUsername();
        return username;
    }
}
