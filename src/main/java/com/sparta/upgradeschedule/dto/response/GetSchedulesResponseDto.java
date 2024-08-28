package com.sparta.upgradeschedule.dto.response;

import com.sparta.upgradeschedule.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetSchedulesResponseDto {

    private String title;
    private String contents;
    private int replyCount;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String username;

    public GetSchedulesResponseDto(Schedule schedule){
        title = schedule.getTitle();
        contents = schedule.getContents();
        createDate = schedule.getCreatedate();
        updateDate = schedule.getUpdatedate();
        username = schedule.getUsername();
        replyCount = schedule.getReplyList().size();

    }

}
