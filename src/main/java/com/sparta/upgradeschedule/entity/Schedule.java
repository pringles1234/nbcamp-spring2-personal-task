package com.sparta.upgradeschedule.entity;

import com.sparta.upgradeschedule.dto.request.CreateScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "schedules")
@NoArgsConstructor
public class Schedule extends com.sparta.memo.entity.Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String title;
    private String contents;

    @OneToMany
    private List<Reply> replyList = new ArrayList<>();

    public Schedule(CreateScheduleRequestDto createRequestDto) {
        this.username = createRequestDto.getUsername();
        this.title = createRequestDto.getTitle();
        this.contents = createRequestDto.getContents();
    }
}
