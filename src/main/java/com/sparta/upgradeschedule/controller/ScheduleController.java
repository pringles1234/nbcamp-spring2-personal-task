package com.sparta.upgradeschedule.controller;

import com.sparta.upgradeschedule.dto.request.CreateScheduleRequestDto;
import com.sparta.upgradeschedule.dto.request.UpdateScheduleRequestDto;
import com.sparta.upgradeschedule.dto.response.*;
import com.sparta.upgradeschedule.entity.Schedule;
import com.sparta.upgradeschedule.service.ScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
//@RequiredArgsConstructor
public class ScheduleController {

    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedule")
    public CreateScheduleResponseDto createSchedule(@RequestBody CreateScheduleRequestDto createRequestDto){
        return scheduleService.createSchedule(createRequestDto);
    }

    @GetMapping("/schedule/{id}")
    public GetScheduleResponseDto getScheduleById(@PathVariable(value = "id") Long scheduleId){

        Schedule scheduleById = scheduleService.getScheduleById(scheduleId);
        GetScheduleResponseDto getScheduleResponseDto = new GetScheduleResponseDto(scheduleById);
        return getScheduleResponseDto;
    }

    @PutMapping("/schedule/{id}")
    public UpdateScheduleResponseDto updateSchedule(@PathVariable(value = "id") Long scheduleId, @RequestBody UpdateScheduleRequestDto updateScheduleRequestDto){
        return scheduleService.updateSchedule(scheduleId, updateScheduleRequestDto);
    }

    @DeleteMapping("/schedule/{id}")
    public DeleteScheduleResponseDto deleteSchedule(@PathVariable(value = "id") Long scheduleId){
        return scheduleService.deleteSchedule(scheduleId);
    }

    @GetMapping("/schedule")
    public Page<GetSchedulesResponseDto> getSchedules ( //getScheduleList, getSchedulePages
            @RequestParam int page,
            @RequestParam(defaultValue = "10") int size){

        Sort sort = Sort.by("updateDate").descending();

        Pageable pageable = PageRequest.of(page-1, size, sort);

        return scheduleService.getSchedules(pageable);
    }

}
