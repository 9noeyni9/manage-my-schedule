package com.sparta.managemyschedule.common;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.entity.Schedule;

public interface ScheduleFixture extends UserFixture {
    Long TEST_SCHEDULE_ID = 1L;
    String TEST_SCHEDULE_TITLE = "title";
    String TEST_SCHEDULE_CONTENT ="content";

    CreateRequestDto TEST_CREATE_REQUEST_DTO = new CreateRequestDto(TEST_SCHEDULE_TITLE,TEST_SCHEDULE_CONTENT);
    Schedule TEST_SCHEDULE = new Schedule(TEST_USER,TEST_CREATE_REQUEST_DTO);
}
