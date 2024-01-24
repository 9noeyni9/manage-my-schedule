package com.sparta.managemyschedule.repository;

import com.sparta.managemyschedule.dto.responseDto.ReadAllScheduleResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import com.sparta.managemyschedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    Optional<Schedule> findById(Long scheduleId);
    List<ReadResponseDto> findAllByOrderByCreatedDateDesc();
    void deleteById(Long scheduleId);
}
