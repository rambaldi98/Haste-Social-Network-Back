package com.example.backback.controller;

import com.example.backback.dto.request.PostRequest;
import com.example.backback.service.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
@CrossOrigin("*")
public class PostController {
    @Autowired
    private IPostService postService;

    @GetMapping
    public ResponseEntity<?> getAllPost(){
        return ResponseEntity.ok(postService.getAllPost());
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest){

        return  ResponseEntity.ok("");
    }
}
