package com.example.backback.domain.entity.friend;

import com.example.backback.domain.entity.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User userone;

    @ManyToOne(fetch = FetchType.EAGER)
    private User usertwo;

    private int status;
}
