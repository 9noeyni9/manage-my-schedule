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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Schedule(User user,CreateRequestDto createRequestDto) {
        this.user = user;
        this.title = createRequestDto.getTitle();
        this.content = createRequestDto.getContent();
    }

    public void settingSchedule(String title, String content) { // 테스트용 값 setting
        this.title = title;
        this.content = content;
    }

    public void update(User user, UpdateScheduleRequest updateScheduleRequest) {
        this.user = user;
        this.title = updateScheduleRequest.getTitle();
        this.content = updateScheduleRequest.getContent();
    }
}
