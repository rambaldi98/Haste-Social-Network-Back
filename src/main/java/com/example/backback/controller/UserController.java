package com.example.backback.controller;

import com.example.backback.security.userprincal.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @GetMapping("")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("hello user" );
    }
}
