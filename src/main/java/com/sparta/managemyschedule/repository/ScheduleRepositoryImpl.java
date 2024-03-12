package com.sparta.managemyschedule.repository;

import static com.sparta.managemyschedule.entity.QSchedule.schedule;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<ReadResponseDto> findAll(Pageable pageable) {
        List<ReadResponseDto> list = jpaQueryFactory
            .select(Projections.constructor(ReadResponseDto.class,
                schedule.id,
                schedule.title,
                schedule.content,
                schedule.createdDate))
            .from(schedule)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = jpaQueryFactory
            .select(schedule.count())
            .from(schedule)
            .fetchOne();
        return new PageImpl<>(list, pageable, count);
    }
}
