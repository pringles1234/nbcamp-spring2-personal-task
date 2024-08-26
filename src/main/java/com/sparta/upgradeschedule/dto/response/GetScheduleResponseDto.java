package com.sparta.upgradeschedule.dto.response;

import com.sparta.upgradeschedule.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetScheduleResponseDto {

    private Long scheduleId;
    private String username;
    private String title;
    private String contents;
    private LocalDateTime updatedate;

    public GetScheduleResponseDto(Schedule schedule){
        this.scheduleId = schedule.getScheduleId();
        this.username = schedule.getUsername();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.updatedate = schedule.getUpdatedate();
    }
}
