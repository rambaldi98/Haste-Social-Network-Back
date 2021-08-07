package com.example.backback.domain.entity.post;

import com.example.backback.domain.entity.User;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.Instant;
import java.util.Collection;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Instant time;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

// 1 nnguoi like 1 lan
    // one to many

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)

//    @EqualsAndHashCode.Exclude // không sử dụng trường này trong
//    @JoinTable(name = "like_post", //Tạo ra một join table
//            joinColumns = @JoinColumn(name = "like_id"),  // TRong đó, khóa ngoại chính là s_id trỏ tới class hiện tại (g)
//            inverseJoinColumns = @JoinColumn(name = "post_id") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (posst)
//    )

    private Collection<Post> post;


    public Like() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Post> getPost() {
        return post;
    }

    public void setPost(Collection<Post> post) {
        this.post = post;
    }


}
