package com.sparta.managemyschedule.repository;

import com.sparta.managemyschedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,String> {
    Optional<Schedule> findById(Long scheduleId);
    void deleteById(Long scheduleId, String insertPwd);
}
