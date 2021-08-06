package com.example.backback.repository;

import com.example.backback.domain.entity.User;
import com.example.backback.domain.entity.friend.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFriendshipRepository extends JpaRepository<Friend, Long> {

    Optional<Friend> findByUseroneAndAndUsertwo(User userOne, User userTwo);
}
