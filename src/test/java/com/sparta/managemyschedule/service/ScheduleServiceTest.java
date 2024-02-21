package com.sparta.managemyschedule.service;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.entity.User;
import com.sparta.managemyschedule.repository.ScheduleRepository;
import com.sparta.managemyschedule.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class ScheduleServiceTest {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    UserRepository userRepository;
    @Mock
    User user;

    @BeforeEach
    void setup(){
        user = new User("testuser1","testuser1","testuser1@test.com");
        userRepository.save(user);
    }

    @Test
    @DisplayName("스케줄 생성 성공")
    void 스케줄생성_성공(){
        // given
        CreateRequestDto createRequestDto = new CreateRequestDto("스케줄 생성 테스트 하는 날","성공해야 다음 테스트 가능!");

        // when
        CreateResponseDto schedule = scheduleService.createSchedule(user, createRequestDto);

        // then
        assertNotNull(schedule.getScheduleId());
        assertEquals(createRequestDto.getTitle(), schedule.getTitle());
        assertEquals(createRequestDto.getContent(),schedule.getContent());
    }
}