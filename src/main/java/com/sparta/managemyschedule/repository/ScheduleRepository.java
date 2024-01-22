package com.sparta.managemyschedule.repository;

import com.sparta.managemyschedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,String> {
}
