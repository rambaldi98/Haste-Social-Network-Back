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

import java.util.List;
import java.util.Optional;

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
    @Autowired
    UserServiceImpl userService;
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
    // lay 1 list user
    @GetMapping("/getpost/{user}")
    public ResponseEntity<?> getPostByUsername(@PathVariable String user){
        // lay ra user
        Optional<User> userPost = userService.findByUsername(user);
        // tim trong sv
        List a =  postService.findByUser(userPost.get());

        return new ResponseEntity<>(a, HttpStatus.OK);
    }
    // xoa post phai trung vs user hien tai
    @PostMapping("/remove/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {

        if(userDetailService.getCurrentUser().getUsername().equals( postService.getUsernameById(id) )){
            postService.remove(id);
            return new ResponseEntity<>(new ResponMessage("delete done"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponMessage("not delete"), HttpStatus.BAD_REQUEST);

    }

    @GetMapping("getPost/{id}")
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable Long id){
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }



}
