package com.sparta.managemyschedule.service;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.requestDto.UpdateScheduleRequest;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import com.sparta.managemyschedule.entity.Schedule;
import com.sparta.managemyschedule.entity.User;
import com.sparta.managemyschedule.repository.ScheduleRepository;
import com.sparta.managemyschedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class ScheduleServiceTest {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    UserRepository userRepository;
    @Mock
    User user, userForComparison;

    @Mock
    Schedule schedule, scheduleForComparison;

    @BeforeEach
    void setup(){
        user = new User("testuser1","testuser1","testuser1@test.com");
        userRepository.save(user);

        userForComparison = new User("testuser2","testuser2","testuser2@test.com");
        userRepository.save(userForComparison);

        scheduleForComparison = scheduleRepository.save(new Schedule(userForComparison,new CreateRequestDto("userForComparison의 일정","user에겐 상세 조회를 제외하고 권한 없음")));
    }

    @Test
    @DisplayName("스케줄 생성 성공")
    void 생성_성공(){
        // given
        CreateRequestDto createRequestDto = new CreateRequestDto("스케줄 생성 테스트 하는 날","성공해야 다음 테스트 가능!");

        // when
        CreateResponseDto schedule = scheduleService.createSchedule(user, createRequestDto);

        // then
        assertNotNull(schedule.getScheduleId());
        assertEquals(createRequestDto.getTitle(), schedule.getTitle());
        assertEquals(createRequestDto.getContent(),schedule.getContent());
    }

    @Test
    @DisplayName("스케줄 전체 조회 성공")
    @Transactional
    void 전체조회_성공(){
        //given
        CreateRequestDto createRequestDto1 = new CreateRequestDto("스케줄 전체 조회 테스트 1","첫번째!");
        CreateRequestDto createRequestDto2 = new CreateRequestDto("스케줄 전체 조회 테스트 2","두번째!");
        CreateRequestDto createRequestDto3 = new CreateRequestDto("스케줄 전체 조회 테스트 3","세번째!");
        CreateRequestDto createRequestDto4 = new CreateRequestDto("스케줄 전체 조회 테스트 4","네번째!");

        scheduleRepository.save(new Schedule(user, createRequestDto1));
        scheduleRepository.save(new Schedule(user, createRequestDto2));
        scheduleRepository.save(new Schedule(user, createRequestDto3));
        scheduleRepository.save(new Schedule(user, createRequestDto4));

        // when
        Page<ReadResponseDto> allScheduleList = scheduleService.readAll(user,1,20, "id",true);

        // then
        assertEquals(4,allScheduleList.getTotalElements());
    }

    @Test
    @DisplayName("스케줄 상세 조회 성공")
    void 상세조회_성공1(){
        // given
        CreateRequestDto createRequestDto = new CreateRequestDto("스케줄 상세 조회 성공 테스트 ","성공~~");
        schedule = scheduleRepository.save(new Schedule(user,createRequestDto));

        // when
        scheduleService.readSchedule(schedule.getId());

        // then
        assertDoesNotThrow(() -> scheduleService.readSchedule(schedule.getId()));
    }

    @Test
    @DisplayName("작성자 구분없이 스케줄 상세 조회 성공")
    void 상세조회_성공2(){
        // given
        CreateRequestDto createRequestDto = new CreateRequestDto("스케줄 상세 조회 성공 테스트 ","성공~~");
        schedule = scheduleRepository.save(new Schedule(user,createRequestDto));

        // when
        scheduleService.readSchedule(scheduleForComparison.getId());

        // then
        assertDoesNotThrow(() -> scheduleService.readSchedule(schedule.getId()));
    }

    @Test
    @DisplayName("존재하지 않는 스케줄 상세 조회 실패")
    void 상세조회_실패(){
        // given
        CreateRequestDto createRequestDto = new CreateRequestDto("스케줄 상세 조회 실패 테스트 ","실패!!!");
        schedule = scheduleRepository.save(new Schedule(user,createRequestDto));

        // when
        scheduleService.readSchedule(schedule.getId());

        // then
        assertThrows(NoSuchElementException.class, () -> scheduleService.readSchedule(schedule.getId() +1L));
    }

    @Test
    @DisplayName("스케줄 제목 수정 성공")
    @Transactional // 테스트 코드에서 롤백해주는 용도
    void 제목수정_성공(){
        // given
        CreateRequestDto createRequestDto = new CreateRequestDto("스케줄 제목 수정 성공 테스트 ","제목 수정하기");
        schedule = scheduleRepository.save(new Schedule(user,createRequestDto));

        UpdateScheduleRequest updateScheduleRequest = new UpdateScheduleRequest("제목이 수정이 되려나?",createRequestDto.getContent());

        // when
        scheduleRepository.findById(schedule.getId());
        scheduleService.updateSchedule(user,updateScheduleRequest,schedule.getId());

        // then
        assertDoesNotThrow(() -> scheduleService.updateSchedule(user,updateScheduleRequest,schedule.getId()));
        assertEquals(updateScheduleRequest.getTitle(),schedule.getTitle());
    }

    @Test
    @DisplayName("스케줄 내용 수정 성공")
    @Transactional
    void 내용수정_성공(){
        // given
        CreateRequestDto createRequestDto = new CreateRequestDto("스케줄 내용 수정 성공 테스트 ","내용 수정하기");
        schedule = scheduleRepository.save(new Schedule(user,createRequestDto));

        UpdateScheduleRequest updateScheduleRequest = new UpdateScheduleRequest(createRequestDto.getTitle(),"내용 수정 되겠지?????");

        // when
        scheduleRepository.findById(schedule.getId());
        scheduleService.updateSchedule(user,updateScheduleRequest,schedule.getId());

        // then
        assertDoesNotThrow(() -> scheduleService.updateSchedule(user,updateScheduleRequest,schedule.getId()));
        assertEquals(updateScheduleRequest.getContent(),schedule.getContent());
    }

    @Test
    @DisplayName("스케줄 삭제 성공 테스트")
    @Transactional
    void 삭제_성공(){
        // given
        CreateRequestDto createRequestDto = new CreateRequestDto("스케줄 내용 삭제 성공 테스트 ","삭제될 테스트~");
        schedule = scheduleRepository.save(new Schedule(user,createRequestDto));

        // when
        scheduleRepository.findById(schedule.getId());
        scheduleService.deleteSchedule(user, schedule.getId());

        // then
        Optional<Schedule> checkSchedule = scheduleRepository.findById(schedule.getId());
        assert(checkSchedule).isEmpty();
    }

    @Test
    @DisplayName("이미 삭제된 스케줄 삭제 실패 테스트")
    @Transactional
    void 삭제_실패(){
        // given
        CreateRequestDto createRequestDto = new CreateRequestDto("삭제 될 테스트","삭제될 테스트~");
        schedule = scheduleRepository.save(new Schedule(user,createRequestDto));
        long alreadDeleteScheduleId = schedule.getId();

        // when
        scheduleRepository.findById(schedule.getId());
        scheduleRepository.deleteByUserAndId(user,schedule.getId());

        // then
        assertThrows(NoSuchElementException.class,() -> scheduleService.deleteSchedule(user,alreadDeleteScheduleId));
    }
}