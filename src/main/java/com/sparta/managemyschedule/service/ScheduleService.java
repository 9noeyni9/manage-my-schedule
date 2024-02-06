package com.sparta.managemyschedule.service;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.requestDto.UpdateScheduleRequest;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import com.sparta.managemyschedule.entity.Schedule;
import com.sparta.managemyschedule.entity.User;
import com.sparta.managemyschedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public CreateResponseDto createSchedule(User user, CreateRequestDto createRequestDto) {
        Schedule saveSchedule = scheduleRepository.save(new Schedule(user, createRequestDto));
        return new CreateResponseDto(saveSchedule);
    }

    public ReadResponseDto readSchedule(Long scheduleId) throws NoSuchElementException {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(NoSuchElementException::new);
        ReadResponseDto readResponseDto = new ReadResponseDto(schedule);
        return readResponseDto;
    }

    public Page<ReadResponseDto> readAll(User user,int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Schedule> scheduleList = scheduleRepository.findAllByOrderByCreatedDateDesc(user,pageable);

        return scheduleList.map(ReadResponseDto::new);
    }

    @Transactional
    public void updateSchedule(User user,UpdateScheduleRequest updateScheduleRequest, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(NoSuchElementException::new);
        schedule.update(user, updateScheduleRequest);
    }

    @Transactional
    public void deleteSchedule(User user, Long scheduleId) {
        scheduleRepository.findById(scheduleId).orElseThrow(NoSuchElementException::new);
        scheduleRepository.deleteById(user,scheduleId);
    }
}
