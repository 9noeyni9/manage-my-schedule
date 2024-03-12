package com.sparta.managemyschedule.service;

import com.sparta.managemyschedule.dto.requestDto.SignupRequestDto;
import com.sparta.managemyschedule.entity.User;
import com.sparta.managemyschedule.global.enumeration.ErrorCode;
import com.sparta.managemyschedule.global.exception.InvalidInputException;
import com.sparta.managemyschedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        userRepository.findByUsername(username).orElseThrow(() -> new InvalidInputException(
            ErrorCode.ALREADY_EXISTS_USERNAME));

        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new InvalidInputException(ErrorCode.ALREADY_EXISTS_EMAIL);
        }

        User user = new User(username, password, email);
        userRepository.save(user);
    }
}
