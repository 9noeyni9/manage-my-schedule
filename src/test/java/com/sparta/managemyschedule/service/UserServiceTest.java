package com.sparta.managemyschedule.service;

import com.sparta.managemyschedule.dto.requestDto.SignupRequestDto;
import com.sparta.managemyschedule.entity.User;
import com.sparta.managemyschedule.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;


    @Test
    @DisplayName("회원 가입 성공")
    void 회원가입_성공(){

        // given
        SignupRequestDto signupRequestDto = new SignupRequestDto("test1234","test1234","test1234@naver.com");

        // when
        UserService userService = new UserService(userRepository, passwordEncoder);
        userService.signup(signupRequestDto);

        // then
        assertDoesNotThrow(() -> userService.signup(signupRequestDto));
    }

    @Test
    @DisplayName("회원 가입 실패 이름 중복")
    void 회원가입_실패1(){

        // given
        SignupRequestDto signupRequestDto1 = new SignupRequestDto("test2222","test2222","test2222@test.com");
        SignupRequestDto signupRequestDto2 = new SignupRequestDto("test2222","test2222","test2222@test2.com");
        UserService userService = new UserService(userRepository, passwordEncoder);

        User user = new User(signupRequestDto1.getUsername(), signupRequestDto1.getPassword(), signupRequestDto1.getEmail());
        // when
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));

        //then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.signup(signupRequestDto2));
        assertEquals("중복된 아이디가 존재합니다.", exception.getMessage());
    }

    @Test
    @DisplayName("회원 가입 실패 이메일 중복")
    void 회원가입_실패2(){

        // given
        SignupRequestDto signupRequestDto1 = new SignupRequestDto("test0123","test0123","test@test.com");
        SignupRequestDto signupRequestDto2 = new SignupRequestDto("test9876","test9876","test@test.com");
        UserService userService = new UserService(userRepository, passwordEncoder);
        User user = new User(signupRequestDto1.getUsername(), signupRequestDto1.getPassword(), signupRequestDto1.getEmail());

        // when
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));

        //then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.signup(signupRequestDto2));
        assertEquals("중복된 email 입니다.", exception.getMessage());
    }

}