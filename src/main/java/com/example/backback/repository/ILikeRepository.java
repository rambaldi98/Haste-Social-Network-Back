package com.example.backback.repository;

import com.example.backback.domain.entity.post.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikeRepository extends JpaRepository<Like, Long> {
}
