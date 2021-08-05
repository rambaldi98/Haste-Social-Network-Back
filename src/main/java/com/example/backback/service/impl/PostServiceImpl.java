package com.example.backback.service.impl;

import com.example.backback.domain.entity.Post;
import com.example.backback.dto.request.PostRequest;
import com.example.backback.dto.response.PostResponse;
import com.example.backback.repository.IPostRepository;
import com.example.backback.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private IPostRepository postRepository;

//    @Override
//    public PostResponse save(PostRequest postRequest) {
//        return null;
//    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
    @Override
    public void save(Post postRequest) {
        postRepository.save(postRequest);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<PostResponse> getAllPost() {
        postRepository.findAllByUser(userService.getCurrentUser().get());
        return null;
    }

    @Override
    public List<PostResponse> getPostsByUserId(Long id) {
        return null;
    }


}
