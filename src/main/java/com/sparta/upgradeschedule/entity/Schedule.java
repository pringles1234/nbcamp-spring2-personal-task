package com.sparta.upgradeschedule.entity;

import com.sparta.upgradeschedule.dto.request.CreateScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "scheduleapp")
@NoArgsConstructor
public class Schedule extends com.sparta.memo.entity.Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    private String username;
    private String title;
    private String contents;

    public Schedule(CreateScheduleRequestDto createRequestDto) {
        this.username = createRequestDto.getUsername();
        this.title = createRequestDto.getTitle();
        this.contents = createRequestDto.getContents();
    }
}
