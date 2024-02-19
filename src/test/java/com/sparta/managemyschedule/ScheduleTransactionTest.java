package com.sparta.managemyschedule;

import com.sparta.managemyschedule.entity.Schedule;
import com.sparta.managemyschedule.repository.ScheduleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ScheduleTransactionTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    ScheduleRepository scheduleRepository;

//    @Test
//    @Transactional
//    @Rollback
//    @DisplayName("스케줄 등록 성공")
//    void createScheduleTest() {
//        Schedule schedule = new Schedule();
//
//        schedule.settingSchedule("테스트 스케줄 title", "테스트 스케줄 content", "담당자테스트");
//
//        em.persist(schedule);
//    }

}