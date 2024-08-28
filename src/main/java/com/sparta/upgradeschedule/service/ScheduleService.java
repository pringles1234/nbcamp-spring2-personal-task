package com.sparta.upgradeschedule.service;

import com.sparta.upgradeschedule.dto.request.CreateScheduleRequestDto;
import com.sparta.upgradeschedule.dto.request.UpdateScheduleRequestDto;
import com.sparta.upgradeschedule.dto.response.CreateScheduleResponseDto;
import com.sparta.upgradeschedule.dto.response.DeleteScheduleResponseDto;
import com.sparta.upgradeschedule.dto.response.GetSchedulesResponseDto;
import com.sparta.upgradeschedule.dto.response.UpdateScheduleResponseDto;
import com.sparta.upgradeschedule.entity.Schedule;
import com.sparta.upgradeschedule.entity.User;
import com.sparta.upgradeschedule.entity.UserSchedule;
import com.sparta.upgradeschedule.repository.ScheduleRepository;
import com.sparta.upgradeschedule.repository.UserRepository;
import com.sparta.upgradeschedule.repository.UserScheduleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;
    private UserRepository userRepository;
    private UserScheduleRepository userScheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository, UserScheduleRepository userScheduleRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.userScheduleRepository = userScheduleRepository;
    }

    @Transactional
    public CreateScheduleResponseDto createSchedule(CreateScheduleRequestDto createRequestDto) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        // createRequestDto -> Entity
        Schedule schedule = new Schedule(createRequestDto);

        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

//        UserSchedule userSchedule = new UserSchedule(saveSchedule, user);
//        userScheduleRepository.save(userSchedule);


        // Entity -> CreateScheduleResponseDto
        CreateScheduleResponseDto createScheduleResponseDto = new CreateScheduleResponseDto(saveSchedule);

        return createScheduleResponseDto;
    }

    @Transactional(readOnly = true)
    public Schedule getScheduleById(Long scheduleId) {
        return findScheduleById(scheduleId);
    }

    private Schedule findScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
    }

    @Transactional
    public UpdateScheduleResponseDto updateSchedule(Long scheduleId, UpdateScheduleRequestDto updateScheduleRequestDto) {
        try{
            // 1. 스케줄 존재하는지 확인 및 가져오기 (getScheduleById 메서드 사용)
            Schedule schedule = findScheduleById(scheduleId);

            // 입력받은 일정 담당자와 유저테이블 비교
                for(Long assinedUserId : updateScheduleRequestDto.getAssignedUserIds()) {
                    userRepository.findById(assinedUserId)
                            .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
                }

                for(Long assinedUserId : updateScheduleRequestDto.getAssignedUserIds()) {
                    User user = userRepository.findById(assinedUserId).get();
                    if( userScheduleRepository.existsByUserAndSchedule(user, schedule) == true) {
                        throw new IllegalArgumentException("이미 등록된 사용자입니다.");
                    }
                }
                for(Long assinedUserId : updateScheduleRequestDto.getAssignedUserIds()) {
                    // UserSchedule Entity 생성
                    User user = userRepository.findById(assinedUserId).get();
                    UserSchedule userSchedule = new UserSchedule(schedule,user);
                    // Schedule에 UserSchedule 연관 설정
                    schedule.addUserSchedule(userSchedule);
                    // UserSchedule 저장
                    userScheduleRepository.save(userSchedule);
                }

            // 2. 스케줄 내용 수정
            schedule.updateSchedule(updateScheduleRequestDto);

            // 3. 변경된 스케줄 저장
            scheduleRepository.save(schedule);

            // 4. 수정된 데이터를 포함한 응답 반환
            return new UpdateScheduleResponseDto(schedule);
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

        return pageSchedule.map(GetSchedulesResponseDto::new);
    }
}
