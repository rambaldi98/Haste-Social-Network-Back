package com.example.backback.service.impl;


import com.example.backback.domain.entity.User;
import com.example.backback.repository.IUserRepository;
import com.example.backback.security.userprincal.UserPrinciple;
import com.example.backback.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserPrinciple getCurrentUser() {
      return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
}
