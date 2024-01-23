package com.sparta.managemyschedule.service;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.requestDto.UpdateScheduleRequest;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import com.sparta.managemyschedule.entity.Schedule;
import com.sparta.managemyschedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Component
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    public CreateResponseDto createSchedule(CreateRequestDto createRequestDto){
        Schedule schedule = new Schedule(createRequestDto);

        Schedule saveSchedule = scheduleRepository.save(schedule);

        CreateResponseDto createResponseDto = new CreateResponseDto(saveSchedule);
        return createResponseDto;
    }

    public ReadResponseDto readSchedule(Long scheduleId) throws NoSuchElementException {
        Schedule readSchedule = scheduleRepository.findById(scheduleId).orElseThrow(NoSuchElementException::new);
        ReadResponseDto readResponseDto = new ReadResponseDto(readSchedule);

        return readResponseDto;
    }

    @Transactional
    public void updateSchedule(UpdateScheduleRequest updateScheduleRequest, Long scheduleId) {
        Schedule updateId = scheduleRepository.findById(scheduleId).orElseThrow(NoSuchElementException::new);

        updateId.update(updateScheduleRequest);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        // 비밀번호 비교 후 삭제 구현 예정
        scheduleRepository.findById(scheduleId).orElseThrow(NoSuchElementException::new);

        scheduleRepository.deleteById(scheduleId);
    }
}
