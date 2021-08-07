package com.example.backback.service;


import com.example.backback.domain.entity.User;
import com.example.backback.domain.entity.post.LikePost;

import java.util.Optional;

public interface ILikeService {
    Optional<LikePost> findByUser(User user);
}
