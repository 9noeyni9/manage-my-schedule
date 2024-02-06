package com.sparta.managemyschedule.repository;

import com.sparta.managemyschedule.entity.Schedule;
import com.sparta.managemyschedule.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    Optional<Schedule> findById(Long scheduleId);
    Page<Schedule> findAllByOrderByCreatedDateDesc(User user, Pageable pageable);
    void deleteById(User user, Long scheduleId);
}
