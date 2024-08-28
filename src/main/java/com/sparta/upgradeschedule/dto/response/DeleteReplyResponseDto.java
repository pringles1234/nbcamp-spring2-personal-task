package com.sparta.upgradeschedule.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteReplyResponseDto {

    private Long replyId;

    public DeleteReplyResponseDto(Long replyId){
        this.replyId = replyId;
    }
}
