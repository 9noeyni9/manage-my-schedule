package com.sparta.managemyschedule.dto.requestDto;

import com.sparta.managemyschedule.common.Data;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SignupRequestDtoTest {

    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    @Test
    @DisplayName("사용자 요청 Dto 생성 성공")
    void 유저DTO생성성공() {
        // given
        SignupRequestDto signupRequestDto = new SignupRequestDto(Data.testUser.getUsername(),
            Data.testUser.getPassword(), "testtest@test.com");

        // when
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(signupRequestDto);

        // then
        Assertions.assertThat(violations).isEmpty();
    }
}
