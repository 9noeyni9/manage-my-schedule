package com.sparta.managemyschedule.service;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.requestDto.DeleteScheduleRequestDto;
import com.sparta.managemyschedule.dto.requestDto.UpdateScheduleRequest;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import com.sparta.managemyschedule.entity.Schedule;
import com.sparta.managemyschedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public CreateResponseDto createSchedule(CreateRequestDto createRequestDto) {
        Schedule schedule = new Schedule(createRequestDto);
        Schedule saveSchedule = scheduleRepository.save(schedule);
        CreateResponseDto createResponseDto = new CreateResponseDto(saveSchedule);
        return createResponseDto;
    }

    public ReadResponseDto readSchedule(Long scheduleId) throws NoSuchElementException {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(NoSuchElementException::new);
        ReadResponseDto readResponseDto = new ReadResponseDto(schedule);
        return readResponseDto;
    }

    public List<ReadResponseDto> readAll() {

        List<Schedule> scheduleList = scheduleRepository.findAllByOrderByCreatedDateDesc();
        List<ReadResponseDto> scheduleListToDto = scheduleList.stream()
                .map(m -> new ReadResponseDto(m)).collect(Collectors.toList());

        return scheduleListToDto;
    }

    @Transactional
    public void updateSchedule(UpdateScheduleRequest updateScheduleRequest, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(NoSuchElementException::new);
        if (Objects.equals(schedule.getPassword(), updateScheduleRequest.getInsertPwd())) {
            schedule.update(updateScheduleRequest);
        } else {
            throw new NoSuchElementException("비밀번호가 일치하지 않습니다.");
        }
    }

    @Transactional
    public void deleteSchedule(Long scheduleId, DeleteScheduleRequestDto deleteScheduleRequestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(NoSuchElementException::new);

        if(Objects.equals(schedule.getPassword(),deleteScheduleRequestDto.getInsertPwd()))
            scheduleRepository.deleteById(scheduleId);
        else
            throw new NoSuchElementException("비밀번호가 일치하지 않습니다.");
    }
}
