package com.example.backback.controller;

import com.example.backback.domain.entity.User;
import com.example.backback.dto.request.SignInForm;
import com.example.backback.dto.response.ResponMessage;
import com.example.backback.security.jwt.JwtProvider;
import com.example.backback.security.userprincal.UserPrinciple;
import com.example.backback.service.impl.RoleServiceImpl;
import com.example.backback.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/api/admin")
@RestController
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;


    ;
    @GetMapping
    public ResponseEntity<?> test(){
//        System.out.println( SecurityContextHolder.getContext().getAuthentication());
            UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userPrinciple.getPassword());
    }

    @PostMapping("/change/password")
    public  ResponseEntity<?> change(@Valid @RequestBody SignInForm signInForm) {
//        if()
//        SecurityContextHolder.getContext().getAuthentication().getPrincipal()

        UserPrinciple userPrinciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(userPrinciple + signInForm.getUsername());
        // tim kiem user trong data base
        if(signInForm.getUsername().equals(userPrinciple.getUsername()))
        {
//            return ResponseEntity.ok("co the doi mat khau");
            Optional<User> user = userService.findByUsername(signInForm.getUsername());
            user.get().setPassword(passwordEncoder.encode(signInForm.getPassword()));
            userService.save(user.get());
          jwtProvider.createToken(authenticationManager.authenticate(SecurityContextHolder.getContext().getAuthentication()));
//            System.out.println(token);
            return  new ResponseEntity<> ( new ResponMessage("done"),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponMessage("not found user"), HttpStatus.OK);
        }
//        return new ResponseEntity<>(new ResponMessage("not fount user"), HttpStatus.OK);
    }
}
