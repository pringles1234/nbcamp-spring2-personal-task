package com.sparta.upgradeschedule.service;

import com.sparta.upgradeschedule.dto.request.CreateScheduleRequestDto;
import com.sparta.upgradeschedule.dto.request.UpdateScheduleRequestDto;
import com.sparta.upgradeschedule.dto.response.CreateScheduleResponseDto;
import com.sparta.upgradeschedule.dto.response.DeleteScheduleResponseDto;
import com.sparta.upgradeschedule.dto.response.GetSchedulesResponseDto;
import com.sparta.upgradeschedule.dto.response.UpdateScheduleResponseDto;
import com.sparta.upgradeschedule.entity.Schedule;
import com.sparta.upgradeschedule.repository.ScheduleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public CreateScheduleResponseDto createSchedule(CreateScheduleRequestDto createRequestDto) {
        // createRequestDto -> Entity
        Schedule schedule = new Schedule(createRequestDto);

        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        // Entity -> CreateScheduleResponseDto
        CreateScheduleResponseDto getScheduleResponseDto = new CreateScheduleResponseDto(saveSchedule);

        return getScheduleResponseDto;
    }

    @Transactional(readOnly = true)
    public Schedule getScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
    }

    @Transactional
    public UpdateScheduleResponseDto updateSchedule(Long scheduleId, UpdateScheduleRequestDto updateScheduleRequestDto) {
        try{
            // 1. 스케줄 존재하는지 확인 및 가져오기 (getScheduleById 메서드 사용)
            Schedule schedule = getScheduleById(scheduleId);

            // 2. 스케줄 내용 수정
            schedule.updateSchedule(updateScheduleRequestDto);

            // 3. 변경된 스케줄 저장
            scheduleRepository.save(schedule);

            // 4. 수정된 데이터를 포함한 응답 반환
            return new UpdateScheduleResponseDto(schedule.getScheduleId(), schedule.getUsername(), schedule.getTitle(), schedule.getContents(), schedule.getUpdatedate());
        }catch (Exception e){
            throw new IllegalArgumentException("스케줄 업데이트 중 문제가 발생했습니다.", e);
        }
    }

    @Transactional
    public DeleteScheduleResponseDto deleteSchedule(Long scheduleId) {
        try {
            scheduleRepository.deleteById(scheduleId);
            return new DeleteScheduleResponseDto(scheduleId);
        }
        catch(Exception e) {
            throw new IllegalArgumentException("이미 존재하지 않는 스케줄입니다.");
        }
    }

    public Page<GetSchedulesResponseDto> getSchedules(Pageable pageable){
        Page<Schedule> pageSchedule = scheduleRepository.findAll(pageable);
        Page<GetSchedulesResponseDto> getSchedulesResponseDtos = pageSchedule.map(
                m -> new GetSchedulesResponseDto(m)
        );
        return getSchedulesResponseDtos;
    }
}
