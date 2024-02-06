package com.sparta.managemyschedule.controller;

import com.sparta.managemyschedule.auth.security.UserDetailsImpl;
import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.requestDto.UpdateScheduleRequest;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import com.sparta.managemyschedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<CreateResponseDto> createSchedule(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CreateRequestDto createRequestDto){
        CreateResponseDto createResponseDto = scheduleService.createSchedule(userDetails.getUser(), createRequestDto);
        return ResponseEntity.ok().body(createResponseDto);
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ReadResponseDto> readSchedule(@PathVariable Long scheduleId){
        ReadResponseDto readResponseDto = scheduleService.readSchedule(scheduleId);
        return ResponseEntity.ok().body(readResponseDto);
    }

    @GetMapping("/schedules")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Page<ReadResponseDto>> readAll(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc
    ){
        Page<ReadResponseDto> scheduleList = scheduleService.readAll(userDetails.getUser(),page-1,size,sortBy,isAsc);
        return ResponseEntity.ok().body(scheduleList);
    }

    @PutMapping("/schedules/{scheduleId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Void> updateSchedule(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UpdateScheduleRequest updateScheduleRequest, @PathVariable Long scheduleId){
        scheduleService.updateSchedule(userDetails.getUser(),updateScheduleRequest,scheduleId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/schedules/{scheduleId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Void> deleteSchedule(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long scheduleId){
        scheduleService.deleteSchedule(userDetails.getUser(),scheduleId);
        return ResponseEntity.noContent().build();
    }
}
