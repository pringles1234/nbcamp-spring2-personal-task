package com.sparta.upgradeschedule.dto.response;

import com.sparta.upgradeschedule.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateUserResponseDto {

    private Long userId;
    private String username;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public CreateUserResponseDto(User user){
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createDate = user.getCreateDate();
        this.updateDate = user.getUpdateDate();
    }
}
