package com.sparta.upgradeschedule.dto.response;

import com.sparta.upgradeschedule.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateScheduleResponseDto {
    private Long scheduleId;
    private Long userId;
    private String title;
    private String contents;
    private LocalDateTime updateDate;

    public UpdateScheduleResponseDto(Long scheduleId, Long userId, String title, String contents, LocalDateTime updateDate){
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.updateDate = updateDate;
    }

    public UpdateScheduleResponseDto(Schedule schedule) {
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.updateDate = updateDate;
    }
}

