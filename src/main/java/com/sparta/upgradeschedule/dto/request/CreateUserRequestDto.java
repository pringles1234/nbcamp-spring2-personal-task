package com.sparta.upgradeschedule.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateUserRequestDto {
    private Long userID;
    private String username;
    private String email;
}
