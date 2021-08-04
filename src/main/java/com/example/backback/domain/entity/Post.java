package com.example.backback.domain.entity;


import com.sun.istack.Nullable;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Nullable
    @Lob
    private String description;

    private Integer status = 2;// private = 0,1 ,2
    private Integer likeCount= 0;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;


    private Instant createDate;
}
