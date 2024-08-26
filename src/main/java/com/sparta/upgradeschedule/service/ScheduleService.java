package com.sparta.upgradeschedule.service;

import com.sparta.upgradeschedule.dto.request.CreateScheduleRequestDto;
import com.sparta.upgradeschedule.dto.response.CreateScheduleResponseDto;
import com.sparta.upgradeschedule.dto.response.DeleteScheduleResponseDto;
import com.sparta.upgradeschedule.entity.Schedule;
import com.sparta.upgradeschedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public CreateScheduleResponseDto createSchedule(CreateScheduleRequestDto createRequestDto) {
        // createRequestDto -> Entity
        Schedule schedule = new Schedule(createRequestDto);

        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        // Entity -> CreateScheduleResponseDto
        CreateScheduleResponseDto getScheduleResponseDto = new CreateScheduleResponseDto(saveSchedule);

        return getScheduleResponseDto;
    }

    public Schedule getScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
    }

    public DeleteScheduleResponseDto deleteSchedule(Long scheduleId) {

        try {
            scheduleRepository.deleteById(scheduleId);
            return new DeleteScheduleResponseDto(scheduleId);
        }
        catch(Exception e) {
            throw new IllegalArgumentException("이미 존재하지 않는 스케줄입니다.");
        }
    }

}
