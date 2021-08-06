package com.example.backback.service;

import com.example.backback.domain.entity.User;
import com.example.backback.domain.entity.friend.Friend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IFriendShipService {


    Iterable<Friend> findAll();

    Friend save(Friend friend);

    Optional<Friend> findById(Long id);

    void delete(Long id);

    Iterable<Friend> findAllByUser();

    Optional<Friend> findByUseroneAndAndUsertwo(User userOne, User userTwo);

    Iterable<Friend> findAllFriendByStatus( Long user_id, Integer status);

}
