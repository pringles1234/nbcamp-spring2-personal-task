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
    private LocalDateTime updatedate;

    public GetReplyDto(Long replyId, String username, String contents, LocalDateTime updatedate) {
        this.replyId = replyId;
        this.username = username;
        this.contents = contents;
        this.updatedate = updatedate;

    }
}
