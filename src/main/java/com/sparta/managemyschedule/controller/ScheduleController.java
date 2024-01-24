package com.sparta.managemyschedule.controller;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.requestDto.DeleteScheduleRequestDto;
import com.sparta.managemyschedule.dto.requestDto.UpdateScheduleRequest;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import com.sparta.managemyschedule.entity.Schedule;
import com.sparta.managemyschedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponseDto createSchedule(@RequestBody CreateRequestDto createRequestDto){
        CreateResponseDto createResponseDto = scheduleService.createSchedule(createRequestDto);
        return createResponseDto;
    }

    @GetMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.OK)
    public ReadResponseDto readSchedule(@PathVariable Long scheduleId){
        ReadResponseDto readResponseDto = scheduleService.readSchedule(scheduleId);
        return readResponseDto;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReadResponseDto> readAll(){
        List<ReadResponseDto> scheduleList = scheduleService.readAll();
        return scheduleList;
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<Schedule> readAll(){
//        List<Schedule> scheduleList = scheduleService.readAll();
//        return scheduleList;
//    }

    @PutMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSchedule(@RequestBody UpdateScheduleRequest updateScheduleRequest, @PathVariable Long scheduleId){
        scheduleService.updateSchedule(updateScheduleRequest,scheduleId);
    }

    @DeleteMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(@PathVariable Long scheduleId, @RequestBody DeleteScheduleRequestDto deleteScheduleRequestDto){
        scheduleService.deleteSchedule(scheduleId, deleteScheduleRequestDto);
    }
}
