package com.sparta.upgradeschedule.entity;

import com.sparta.upgradeschedule.dto.request.UpdateReplyRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "replies")
@NoArgsConstructor
@AllArgsConstructor
public class Reply extends com.sparta.memo.entity.Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long replyId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "schedue_id", nullable = false)
    private Schedule schedule;


    public Reply(String username, String contents, Schedule schedule) {
        this.username = username;
        this.contents = contents;
        this.schedule = schedule;
    }

    public void updateReply(UpdateReplyRequestDto updateReplyRequestDto) {
        this.username = updateReplyRequestDto.getUsername();
        this.contents = updateReplyRequestDto.getContents();
        this.getUpdateDate();
    }
}
