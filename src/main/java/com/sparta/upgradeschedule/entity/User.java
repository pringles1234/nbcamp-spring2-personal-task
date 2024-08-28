package com.sparta.upgradeschedule.entity;

import com.sparta.upgradeschedule.dto.request.CreateUserRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User extends com.sparta.memo.entity.Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 생성되는 기본 키 설정
    private long userId;  // 기본 키 필드

    @Column(nullable = false)
    private String username;
    private String email;

    public User(CreateUserRequestDto createUserRequestDto) {
        this.username = createUserRequestDto.getUsername();
        this.email = createUserRequestDto.getEmail();
    }
}
