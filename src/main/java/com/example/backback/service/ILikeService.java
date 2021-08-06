package com.example.backback.service;


import com.example.backback.domain.entity.User;
import com.example.backback.domain.entity.post.Like;

import java.util.Optional;

public interface ILikeService {
    Optional<Like> findByUser(User user);
}
