package com.example.backback.service;

import com.example.backback.domain.entity.User;
import com.example.backback.domain.entity.post.CommentPost;

import java.util.Optional;

public interface ICommentPostService {
    CommentPost save(CommentPost commentPost);
    Optional<CommentPost> findById(long id);
    void deleteById(Long id);
    Iterable<CommentPost> getAllCommentByUser(User user);
}
