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

    private int status; // 0zzzzz la chua ket ban , 1 la ket ban , 2 la chan

    public Friend(User userone, User usertwo) {
        this.userone = userone;
        this.usertwo = usertwo;
        this.status = 0;
    }

    public Friend() {

    }
}
