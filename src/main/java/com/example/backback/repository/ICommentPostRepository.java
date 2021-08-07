package com.example.backback.repository;

import com.example.backback.domain.entity.User;
import com.example.backback.domain.entity.post.CommentPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentPostRepository extends JpaRepository<CommentPost,Long> {
    Iterable<CommentPost> findAllByUser(User user);
}
