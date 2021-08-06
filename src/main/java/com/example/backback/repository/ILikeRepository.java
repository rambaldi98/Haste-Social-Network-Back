package com.example.backback.repository;

import com.example.backback.domain.entity.User;
import com.example.backback.domain.entity.post.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUser(User user);
}
