package com.sparta.upgradeschedule.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateScheduleRequestDto {

    private String username;
    private String title;
    private String contents;
}
