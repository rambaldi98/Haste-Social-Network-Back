package com.example.backback.service.impl;

import com.example.backback.domain.entity.User;
import com.example.backback.domain.entity.post.LikePost;
import com.example.backback.repository.ILikeRepository;
import com.example.backback.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements ILikeService {
    @Autowired
    private ILikeRepository likeRepository;

    @Override
    public Optional<LikePost> findByUser(User user) {

        return likeRepository.findByUser(user);
    }
}
