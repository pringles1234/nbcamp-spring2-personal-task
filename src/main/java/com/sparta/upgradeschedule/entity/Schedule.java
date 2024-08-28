package com.sparta.upgradeschedule.entity;

import com.sparta.upgradeschedule.dto.request.CreateScheduleRequestDto;
import com.sparta.upgradeschedule.dto.request.UpdateScheduleRequestDto;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto(Jpa가 id 관리해서 늘려줌), Identity(mysql이 id 관리해서 늘려줌)
    private long scheduleId;
    @Column(nullable = false)

    private Long userId;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "text")
    private String contents;

    @OneToMany(mappedBy = "schedule", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "schedule")
    private List<UserSchedule> userScheduleList = new ArrayList<>();


    public Schedule(CreateScheduleRequestDto createRequestDto) {
        this.userId = createRequestDto.getUserId();
        this.title = createRequestDto.getTitle();
        this.contents = createRequestDto.getContents();
    }

    public void updateSchedule(UpdateScheduleRequestDto updateScheduleRequestDto){
        this.userId = updateScheduleRequestDto.getUserId();
        this.title = updateScheduleRequestDto.getTitle();
        this.contents = updateScheduleRequestDto.getContents();
        this.getUpdateDate();
    }
}
