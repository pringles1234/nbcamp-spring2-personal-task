package com.sparta.upgradeschedule.controller;

import com.sparta.upgradeschedule.dto.request.CreateScheduleRequestDto;
import com.sparta.upgradeschedule.dto.response.CreateScheduleResponseDto;
import com.sparta.upgradeschedule.dto.response.DeleteScheduleResponseDto;
import com.sparta.upgradeschedule.entity.Schedule;
import com.sparta.upgradeschedule.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
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
    public CreateScheduleResponseDto getScheduleById(@PathVariable(value = "id") Long scheduleId){

        Schedule scheduleById = scheduleService.getScheduleById(scheduleId);
        CreateScheduleResponseDto getScheduleResponseDto = new CreateScheduleResponseDto(scheduleById);
        return getScheduleResponseDto;
    }
    @DeleteMapping("/schedule/{id}")
    public DeleteScheduleResponseDto deleteSchedule(@PathVariable(value = "id") Long scheduleId){
        return scheduleService.deleteSchedule(scheduleId);
    }

}
