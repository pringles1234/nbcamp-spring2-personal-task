package com.sparta.upgradeschedule.dto.response;

import com.sparta.upgradeschedule.dto.request.GetReplyDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetReplyResponseDto {
    private Long scheduleId;
    private String title;
    private List<GetReplyDto> replies;

    public GetReplyResponseDto(Long scheduleId, String title, List<GetReplyDto> replies) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.replies = replies;
    }
}
