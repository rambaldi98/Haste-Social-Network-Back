package com.example.backback.service;

import com.example.backback.domain.entity.post.Post;

import java.util.List;

public interface IPostService {
    Post save(Post post);
    List<Post> findAll();
}
