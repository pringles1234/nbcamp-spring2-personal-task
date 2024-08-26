package com.sparta.upgradeschedule.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteScheduleResponseDto {

    private Long scheduleId;

    public DeleteScheduleResponseDto(Long scheduleId){
        this.scheduleId = scheduleId;
    }
}
