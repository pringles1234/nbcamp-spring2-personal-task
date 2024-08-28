package com.sparta.upgradeschedule.dto.response;

import com.sparta.upgradeschedule.entity.Reply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateReplyResponseDto {
    private Long replyId;
    private String username;
    private String contents;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public CreateReplyResponseDto(Reply reply){
        this.replyId = reply.getReplyId();
        this.username = reply.getUsername();
        this.contents = reply.getContents();
        this.createDate = reply.getCreateDate();
        this.updateDate = reply.getUpdateDate();
    }
}
