package com.example.backback.service;

import com.example.backback.domain.entity.Post;
import com.example.backback.dto.request.PostRequest;
import com.example.backback.dto.response.PostResponse;

import java.util.List;
import java.util.Optional;

public interface IPostService {
//    PostResponse save(PostRequest postRequest);
    void delete(Long id);
    Optional<Post> getPostById(Long id);
    List<PostResponse> getAllPost();
    List<PostResponse> getPostsByUserId(Long id);
    void save(Post post);
}
