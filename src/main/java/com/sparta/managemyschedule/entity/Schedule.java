package com.sparta.managemyschedule.entity;

import com.sparta.managemyschedule.dto.requestDto.CreateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name="schedule")
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false,length = 5000)
    private String content;

    @Column(name = "manager")
    private String manager;

    public Schedule(CreateRequestDto createRequestDto){
        this.title = createRequestDto.getTitle();
        this.content = createRequestDto.getContent();
        this.manager = createRequestDto.getManager();
    }

    public void updateSchedule(String title, String content, String manager){
        this.title = title;
        this.content = content;
        this.manager = manager;
    }
}
