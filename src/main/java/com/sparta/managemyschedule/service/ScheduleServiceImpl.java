package com.sparta.managemyschedule.service;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.requestDto.UpdateScheduleRequest;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import com.sparta.managemyschedule.entity.Schedule;
import com.sparta.managemyschedule.entity.User;
import com.sparta.managemyschedule.global.enumeration.ErrorCode;
import com.sparta.managemyschedule.global.exception.InvalidInputException;
import com.sparta.managemyschedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public CreateResponseDto createSchedule(User user, CreateRequestDto createRequestDto) {
        Schedule saveSchedule = scheduleRepository.save(new Schedule(user, createRequestDto));
        return new CreateResponseDto(saveSchedule);
    }

    @Override
    public ReadResponseDto readSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
            .orElseThrow(() -> new InvalidInputException(
                ErrorCode.NOT_VALID_SCHEDULE));
        return new ReadResponseDto(schedule);
    }

    @Override
    public Page<ReadResponseDto> readAll(User user, int page, int size, String sortBy,
        boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Schedule> scheduleList = scheduleRepository.findAllByUserOrderByCreatedDateDesc(user,
            pageable);

        return scheduleList.map(ReadResponseDto::new);
    }

    @Override
    public void updateSchedule(User user, UpdateScheduleRequest updateScheduleRequest,
        Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
            .orElseThrow(() -> new InvalidInputException(
                ErrorCode.NOT_VALID_SCHEDULE));
        schedule.update(user, updateScheduleRequest);
    }

    @Override
    public void deleteSchedule(User user, Long scheduleId) {
        scheduleRepository.findById(scheduleId).orElseThrow(() -> new InvalidInputException(
            ErrorCode.NOT_VALID_SCHEDULE));
        scheduleRepository.deleteByUserAndId(user, scheduleId);
    }
}
