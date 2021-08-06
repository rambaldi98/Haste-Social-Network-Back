package com.example.backback.controller;

import com.example.backback.domain.entity.User;
import com.example.backback.domain.entity.post.Post;
import com.example.backback.dto.request.PostCreate;
import com.example.backback.dto.response.ResponMessage;
import com.example.backback.mapper.PostMapper;
import com.example.backback.security.userprincal.UserDetailService;
import com.example.backback.service.impl.LikeServiceImpl;
import com.example.backback.service.impl.PostServiceImpl;
import com.example.backback.service.impl.RoleServiceImpl;
import com.example.backback.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/post")
@RestController
@CrossOrigin(origins = "*")
public class PostController {
    @Autowired
    PostServiceImpl postService;
    @Autowired
    LikeServiceImpl likeService;
    @Autowired
    UserDetailService userDetailService;
    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostCreate postCreate){
        Post post = PostMapper.build(postCreate);
        User currentUser =   userDetailService.getCurrentUser();
        post.setUser(currentUser);
        postService.save(post);
        return new ResponseEntity(new ResponMessage("create post done"), HttpStatus.OK);
    }

    @GetMapping("/getAllpost")
    public ResponseEntity<?> getAllPost(){

        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getpost/{user}")
    public ResponseEntity<?> getPost(){

        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

}
