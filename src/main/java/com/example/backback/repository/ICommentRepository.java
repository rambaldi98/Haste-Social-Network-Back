package com.example.backback.repository;

import com.example.backback.domain.entity.CommentPost;
import com.example.backback.domain.entity.Post;
import com.example.backback.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICommentRepository extends JpaRepository<CommentPost,Long> {
    List<CommentPost> findAllByPost(Post post);
    List<CommentPost> findAllByUser(User user);

    @Modifying(clearAutomatically=true, flushAutomatically=true)
    @Query(value = "DELETE from CommentPost  where post_id = ?", nativeQuery = true)
    void deleteByPostId(Long postId);
}
