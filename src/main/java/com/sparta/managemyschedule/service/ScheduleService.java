package com.sparta.managemyschedule.service;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.requestDto.DeleteScheduleRequestDto;
import com.sparta.managemyschedule.dto.requestDto.UpdateScheduleRequest;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadAllScheduleResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import com.sparta.managemyschedule.entity.Schedule;
import com.sparta.managemyschedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    Schedule schedule;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public CreateResponseDto createSchedule(CreateRequestDto createRequestDto) {
        schedule = new Schedule(createRequestDto);

        Schedule saveSchedule = scheduleRepository.save(schedule);

        CreateResponseDto createResponseDto = new CreateResponseDto(saveSchedule);
        return createResponseDto;
    }

    public ReadResponseDto readSchedule(Long scheduleId) throws NoSuchElementException {
        schedule = scheduleRepository.findById(scheduleId).orElseThrow(NoSuchElementException::new);
        ReadResponseDto readResponseDto = new ReadResponseDto(schedule);

        return readResponseDto;
    }

    public ReadAllScheduleResponseDto readAll() {
        List<Schedule> scheduleList = scheduleRepository.findAllByOrderByCreatedDateDesc();

        ReadAllScheduleResponseDto readAllScheduleResponseDto = new ReadAllScheduleResponseDto(scheduleList);

        return readAllScheduleResponseDto;
    }

    @Transactional
    public void updateSchedule(UpdateScheduleRequest updateScheduleRequest, Long scheduleId) {
        schedule = scheduleRepository.findById(scheduleId).orElseThrow(NoSuchElementException::new);
        if (Objects.equals(schedule.getPassword(), updateScheduleRequest.getInsertPwd())) {
            schedule.update(updateScheduleRequest);
        } else {
            throw new NoSuchElementException("Invalid password");
        }
    }

    @Transactional
    public void deleteSchedule(Long scheduleId, String insertPwd) {
        schedule = scheduleRepository.findById(scheduleId).orElseThrow(NoSuchElementException::new);

        if(Objects.equals(schedule.getPassword(),insertPwd))
            scheduleRepository.deleteById(scheduleId);
        else
            throw new NoSuchElementException("Invalid password");

    }
}
