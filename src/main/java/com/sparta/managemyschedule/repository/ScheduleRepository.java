package com.sparta.managemyschedule.repository;

import com.sparta.managemyschedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,String> {
    Optional<Schedule> findById(Long scheduleId);
    List<Schedule> findAllByOrderByCreatedDateDesc();
    void deleteById(Long scheduleId);
}
