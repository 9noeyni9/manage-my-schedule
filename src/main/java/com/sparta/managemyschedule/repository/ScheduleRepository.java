package com.sparta.managemyschedule.repository;

import com.sparta.managemyschedule.entity.Schedule;
import com.sparta.managemyschedule.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,Long>, ScheduleRepositoryCustom {
    Optional<Schedule> findById(Long scheduleId);
    Page<Schedule> findAllByUserOrderByCreatedDateDesc(User user, Pageable pageable);
    void deleteByUserAndId(User user, Long scheduleId);
}
