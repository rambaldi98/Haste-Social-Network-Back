package com.example.backback.repository;

import com.example.backback.domain.entity.Post;
import com.example.backback.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByUser(User user);
}
