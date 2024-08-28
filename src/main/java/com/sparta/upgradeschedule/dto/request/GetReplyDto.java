package com.sparta.upgradeschedule.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetReplyDto {

    private Long replyId;
    private String username;
    private String contents;
    private LocalDateTime updateDate;

    public GetReplyDto(Long replyId, String username, String contents, LocalDateTime updateDate) {
        this.replyId = replyId;
        this.username = username;
        this.contents = contents;
        this.updateDate = updateDate;

    }
}
