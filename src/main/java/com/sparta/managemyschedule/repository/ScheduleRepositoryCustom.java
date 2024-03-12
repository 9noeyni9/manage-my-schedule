package com.sparta.managemyschedule.repository;

import com.sparta.managemyschedule.dto.responseDto.ReadResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleRepositoryCustom {

    Page<ReadResponseDto> findAll(Long userId, Pageable pageable);

}
