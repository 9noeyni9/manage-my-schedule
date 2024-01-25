package com.sparta.managemyschedule.controller;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.requestDto.DeleteScheduleRequestDto;
import com.sparta.managemyschedule.dto.requestDto.UpdateScheduleRequest;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import com.sparta.managemyschedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CreateResponseDto> createSchedule(@RequestBody CreateRequestDto createRequestDto){
        CreateResponseDto createResponseDto = scheduleService.createSchedule(createRequestDto);
        return ResponseEntity.ok().body(createResponseDto);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ReadResponseDto> readSchedule(@PathVariable Long scheduleId){
        ReadResponseDto readResponseDto = scheduleService.readSchedule(scheduleId);
        return ResponseEntity.ok().body(readResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ReadResponseDto>> readAll(){
        List<ReadResponseDto> scheduleList = scheduleService.readAll();
        return ResponseEntity.ok().body(scheduleList);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<Void> updateSchedule(@RequestBody UpdateScheduleRequest updateScheduleRequest, @PathVariable Long scheduleId){
        scheduleService.updateSchedule(updateScheduleRequest,scheduleId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId, @RequestBody DeleteScheduleRequestDto deleteScheduleRequestDto){
        scheduleService.deleteSchedule(scheduleId, deleteScheduleRequestDto);
        return ResponseEntity.noContent().build();
    }
}
