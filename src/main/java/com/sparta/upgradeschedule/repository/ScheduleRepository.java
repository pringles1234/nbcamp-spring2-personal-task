package com.sparta.upgradeschedule.repository;

import com.sparta.upgradeschedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Page<Schedule> findAll(Pageable pageable);

    //인터페이스의 디폴트 메서드(없는 게시글 입니다 반복된거 바꿔줄 수 있음)
}
