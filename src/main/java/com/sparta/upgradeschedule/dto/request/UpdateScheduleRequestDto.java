package com.sparta.upgradeschedule.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateScheduleRequestDto {

    private Long userId;
    private String title;
    private String contents;
    private List<Long> assignedUserIds;

}
