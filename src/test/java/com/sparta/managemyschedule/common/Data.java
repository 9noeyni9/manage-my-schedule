package com.sparta.managemyschedule.common;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.requestDto.UpdateScheduleRequest;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
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

    public static ReadResponseDto getDto(){
        Long scheduleId = 1L;
        String title = "test";
        String content ="test";
        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime modifiedDate = LocalDateTime.now();
        ReadResponseDto readResponseDto = new ReadResponseDto(scheduleId,title,content,createdDate,modifiedDate);

        return readResponseDto;
    }

    public static UpdateScheduleRequest getUpdateRequestDto(){
        String title = "test변경";
        String content = "test변경";
        UpdateScheduleRequest updateScheduleRequest = new UpdateScheduleRequest(title,content);

        return updateScheduleRequest;
    }

    public static Schedule getDefferenceSchedule(){
        String username = "testuser2222";
        String password = "testuser2222";
        String email = "testuser2222@test.com";
        User defferenceUser = new User(username,password,email);
        Schedule failCaseSchedule = new Schedule(defferenceUser,new CreateRequestDto("다른 사람이 생성한 일정","실패 케이스!"));
        return failCaseSchedule;
    }
}
