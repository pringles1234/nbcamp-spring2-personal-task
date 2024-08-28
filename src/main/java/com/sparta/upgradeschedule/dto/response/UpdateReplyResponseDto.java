package com.sparta.upgradeschedule.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateReplyResponseDto {

    private Long replyId;
    private String username;
    private String contents;
    private LocalDateTime updateDate;

    public UpdateReplyResponseDto(Long replyId, String username, String contents, LocalDateTime updateDate){
        this.replyId = replyId;
        this.username = username;
        this.contents = contents;
        this.updateDate = updateDate;
    }
}
