package com.sparta.upgradeschedule.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateScheduleResponseDto {
    private Long scheduleId;
    private String username;
    private String title;
    private String contents;
    private LocalDateTime updatedate;

    public UpdateScheduleResponseDto(Long scheduleId, String username, String title, String contents, LocalDateTime updatedate){
        this.scheduleId = scheduleId;
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.updatedate = updatedate;
    }
}

