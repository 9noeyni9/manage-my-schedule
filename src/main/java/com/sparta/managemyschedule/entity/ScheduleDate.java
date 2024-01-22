package com.sparta.managemyschedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class ScheduleDate {
    @CreatedDate
    @Column(name = "createdDate")
    @Temporal(TemporalType.DATE)
    private LocalDate createdDate;

    @LastModifiedDate
    @Column(name = "modifiedDate")
    @Temporal(TemporalType.DATE)
    private LocalDate modifiedDate;
}
