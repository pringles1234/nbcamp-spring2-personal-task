package com.sparta.upgradeschedule.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteUserResponseDto {

    private Long userId;

    public DeleteUserResponseDto(Long userId){
        this.userId = userId;
    }
}
