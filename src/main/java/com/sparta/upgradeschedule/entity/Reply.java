package com.sparta.upgradeschedule.entity;

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
        private Long replyId;
        @Column(nullable = false)
        private String username;
        @Column(nullable = false)
        private String contents;

        @ManyToOne
        @JoinColumn(name = "schedueId")
        private Schedule schedule;
}
