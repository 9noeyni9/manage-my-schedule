package com.sparta.managemyschedule.service;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.requestDto.UpdateScheduleRequest;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import com.sparta.managemyschedule.entity.Schedule;
import com.sparta.managemyschedule.entity.User;
import com.sparta.managemyschedule.repository.ScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @Mock
    ScheduleRepository scheduleRepository;
    
    @Test
    @DisplayName("스케줄 내용 변경")
    void 스케줄변경(){
        //given
        Long scheduleId = 100L;
        
        User user = new User();
        CreateRequestDto createRequestDto = new CreateRequestDto(
                "스케줄 1",
                "카페 가서 친구 만나는 일정"
        );

        UpdateScheduleRequest updateScheduleRequest = new UpdateScheduleRequest(
                "스케줄1",
                "카페 가서 친구 만나려고 했는데 그냥 혼자 책 읽을까?"
        );
        Schedule schedule = new Schedule(user, createRequestDto);
        ScheduleService scheduleService = new ScheduleService(scheduleRepository);
        given(scheduleRepository.findById(scheduleId)).willReturn(Optional.of(schedule));

        //when
        scheduleService.updateSchedule(user,updateScheduleRequest,scheduleId);

        //then
        ReadResponseDto readResponseDto = new ReadResponseDto(schedule);
        assertEquals("그냥 혼자 책 읽을까?",readResponseDto.getContent(),"예상하는 값과 다릅니다!");
    }

}