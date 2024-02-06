package com.sparta.managemyschedule.entity;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import com.sparta.managemyschedule.dto.requestDto.UpdateScheduleRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule extends ScheduleDate {
    @Id
    @Column(name="id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, length = 5000)
    private String content;

    @Column(name = "manager")
    private String manager;

    @Column(name="password",nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Schedule(CreateRequestDto createRequestDto, User user) {
        this.title = createRequestDto.getTitle();
        this.content = createRequestDto.getContent();
        this.manager = createRequestDto.getManager();
        this.user = user;
    }

    public void settingSchedule(String title, String content, String manager) { // 테스트용 값 setting
        this.title = title;
        this.content = content;
        this.manager = manager;
    }

    public void update(UpdateScheduleRequest updateScheduleRequest) {
        this.title = updateScheduleRequest.getTitle();
        this.content = updateScheduleRequest.getContent();
        this.manager = updateScheduleRequest.getManager();
    }
}
