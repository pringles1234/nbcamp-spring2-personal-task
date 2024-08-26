package com.sparta.upgradeschedule.dto.response;

import com.sparta.upgradeschedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleResponseDto {

    private Long scheduleId;
    private String username;
    private String title;
    private String contents;
    private LocalDateTime createdate;
    private LocalDateTime updatedate;

    public CreateScheduleResponseDto(Schedule schedule){
        this.scheduleId = schedule.getScheduleId();
        this.username = schedule.getUsername();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createdate = schedule.getCreatedate();
        this.updatedate = schedule.getUpdatedate();
    }

}
