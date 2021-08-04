package com.example.backback.domain.entity;


import lombok.Data;


import javax.persistence.*;

@Entity
@Data

public class FriendShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User userOne;

    @ManyToOne(fetch = FetchType.EAGER)
    private User userTwo;

    private boolean status;
}
