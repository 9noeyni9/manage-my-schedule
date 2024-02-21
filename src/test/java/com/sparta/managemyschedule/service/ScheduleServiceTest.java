package com.sparta.managemyschedule.service;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import com.sparta.managemyschedule.entity.Schedule;
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
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

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

    @Mock
    Schedule schedule;

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

    @Test
    @DisplayName("스케줄 전체 조회 성공")
    void 스케줄_전체조회_성공(){
        //given
        CreateRequestDto createRequestDto1 = new CreateRequestDto("스케줄 전체 조회 테스트 1","첫번째!");
        CreateRequestDto createRequestDto2 = new CreateRequestDto("스케줄 전체 조회 테스트 2","두번째!");
        CreateRequestDto createRequestDto3 = new CreateRequestDto("스케줄 전체 조회 테스트 3","세번째!");
        CreateRequestDto createRequestDto4 = new CreateRequestDto("스케줄 전체 조회 테스트 4","네번째!");

        scheduleRepository.save(new Schedule(user, createRequestDto1));
        scheduleRepository.save(new Schedule(user, createRequestDto2));
        scheduleRepository.save(new Schedule(user, createRequestDto3));
        scheduleRepository.save(new Schedule(user, createRequestDto4));

        // when
        Page<ReadResponseDto> allScheduleList = scheduleService.readAll(user,1,20, "id",true);

        // then
        assertEquals(4,allScheduleList.getTotalElements());
    }

    @Test
    @DisplayName("스케줄 상세 조회 성공")
    void 스케줄_상세조회_성공(){
        // given
        CreateRequestDto createRequestDto = new CreateRequestDto("스케줄 상세 조회 성공 테스트 ","성공~~");
        schedule = scheduleRepository.save(new Schedule(user,createRequestDto));

        // when
        scheduleService.readSchedule(schedule.getId());

        // then
        assertDoesNotThrow(() -> scheduleService.readSchedule(schedule.getId()));
    }

    @Test
    @DisplayName("스케줄 상세 조회 실패")
    void 스케줄_상세조회_실패(){
        // given
        CreateRequestDto createRequestDto = new CreateRequestDto("스케줄 상세 조회 실패 테스트 ","실패!!!");
        schedule = scheduleRepository.save(new Schedule(user,createRequestDto));

        // when
        scheduleService.readSchedule(schedule.getId());

        // then
        assertThrows(NoSuchElementException.class, () -> scheduleService.readSchedule(schedule.getId() +1L));
    }
}