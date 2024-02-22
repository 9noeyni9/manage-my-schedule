package com.sparta.managemyschedule.common;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.entity.Schedule;
import com.sparta.managemyschedule.entity.User;

import java.time.LocalDateTime;

public class Data {
        static String username = "test0000";
        static String password = "test0000";
        static String email = "test0000@test.com";
        public static User testUser = new User(username,password,email);
    public static Schedule getCreateSuccessSchedule(){
        Schedule schedule = new Schedule(testUser,new CreateRequestDto("테스트!","테스트!"));

        return schedule;
    }

    public static CreateResponseDto getDto(){
        Long scheduleId = 1L;
        String title = "test";
        String content ="test";
        LocalDateTime createdDate = LocalDateTime.now();
        CreateResponseDto createResponseDto = new CreateResponseDto(scheduleId,title,content,createdDate);

        return createResponseDto;
    }
}
