package com.sparta.upgradeschedule.dto.response;

import com.sparta.upgradeschedule.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetScheduleResponseDto {

    private Long scheduleId;
    private Long userId;
    private String title;
    private String contents;
    private LocalDateTime updateDate;

    public GetScheduleResponseDto(Schedule schedule){
        this.scheduleId = schedule.getScheduleId();
        this.userId = schedule.getUserId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.updateDate = schedule.getUpdateDate();
    }
}
