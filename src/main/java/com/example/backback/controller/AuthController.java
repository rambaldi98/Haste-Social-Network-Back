package com.example.backback.controller;

import com.example.backback.domain.entity.Role;
import com.example.backback.domain.entity.RoleName;
import com.example.backback.domain.entity.User;
import com.example.backback.dto.request.SignInForm;
import com.example.backback.dto.request.SignUpForm;
import com.example.backback.dto.response.JwtResponse;
import com.example.backback.dto.response.ResponMessage;
import com.example.backback.security.jwt.JwtProvider;
import com.example.backback.security.userprincal.UserPrinciple;
import com.example.backback.service.impl.RoleServiceImpl;
import com.example.backback.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RequestMapping("/api/auth")
@RestController
@CrossOrigin(origins = "*")
public class AuthController {
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

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {
        if (userService.existsByUsername(signUpForm.getUsername())) {
            return ResponseEntity.badRequest().body(new ResponMessage("Username is exist"));
        }
        if (userService.existsByEmail(signUpForm.getEmail())) {
            return ResponseEntity.badRequest().body(new ResponMessage("Email is exist"));
        }
        if (userService.existsByPhone(signUpForm.getPhone())) {
            return ResponseEntity.badRequest().body(new ResponMessage("Phone is exist"));
        }


        User user = new User(
                signUpForm.getUsername(),
                passwordEncoder.encode(signUpForm.getPassword()),
                signUpForm.getEmail(),
                signUpForm.getPhone(),
                signUpForm.getBirthday(),
                signUpForm.getCity());

        Set<Role> roles = new HashSet<>();

        Role roleUser = new Role();
                    roleUser.setId(2L);
                    roleUser.setName(RoleName.USER);
                    roles.add(roleUser);
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResponMessage("yes"), HttpStatus.OK);
    }


    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse( userPrinciple.getId(),token, userPrinciple.getUsername(), userPrinciple.getAuthorities()));
    }
}
