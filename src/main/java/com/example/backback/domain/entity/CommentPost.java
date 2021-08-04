package com.example.backback.domain.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

import static javax.persistence.FetchType.EAGER;
@Entity
@Data
public class CommentPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    private String text;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "postId")
    private Post post;

    private Integer likeCount = 0;


    private Instant createdDate;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "userId")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
