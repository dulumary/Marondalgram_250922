package com.marondal.marondalgram.like.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="`like`")
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private long postId;
    private long userId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
