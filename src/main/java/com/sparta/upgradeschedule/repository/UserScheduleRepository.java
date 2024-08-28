package com.sparta.upgradeschedule.repository;

import com.sparta.upgradeschedule.entity.Schedule;
import com.sparta.upgradeschedule.entity.User;
import com.sparta.upgradeschedule.entity.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserScheduleRepository extends JpaRepository<UserSchedule, Long> {
    boolean existsByUserAndSchedule(User user, Schedule schedule);
}
