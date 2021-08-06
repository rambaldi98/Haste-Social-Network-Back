package com.example.backback.controller;

import com.example.backback.domain.entity.User;
import com.example.backback.domain.entity.friend.Friend;
import com.example.backback.dto.request.FriendRequestForm;
import com.example.backback.dto.response.ResponMessage;
import com.example.backback.security.userprincal.UserDetailService;
import com.example.backback.service.impl.FriendshipServiceImpl;
import com.example.backback.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/friend")
@RestController
@CrossOrigin(origins = "*")
public class FriendShipController {
    @Autowired
    FriendshipServiceImpl friendshipService;
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    UserServiceImpl userService;
    @PostMapping("/addFriend")

    public ResponseEntity<?> addFriend(@RequestBody FriendRequestForm requestForm) {
        // check xem user name co ton tai trong db hay khong

        User userOne = userDetailService.getCurrentUser();

        Optional<User> userTwo = userService.findByUsername(requestForm.getUsernametwo());

        if(userTwo.isPresent()) return new ResponseEntity<>(new ResponMessage("not find user"), HttpStatus.BAD_REQUEST);
        User usern = userTwo.get();
        // check xem 2 thang kia co la ban hay khong
        Optional<Friend> friendCheck = friendshipService.findByUseroneAndAndUsertwo(userOne,usern);
        if(friendCheck.isPresent()) return new ResponseEntity<>(new ResponMessage("not add friend"), HttpStatus.BAD_REQUEST);
        // ko phai tao moi
        Friend friend = new Friend(userOne,usern);

        return new ResponseEntity<>(friendshipService.save(friend),HttpStatus.OK);
    }
    @PostMapping("/acceptFriend/{id}")
    public ResponseEntity<?> accessFriend(@PathVariable("id") Long id) {
        // lay lai friend

        Optional<Friend> friend = friendshipService.findById(id);
        if(friend.isPresent()) return new ResponseEntity<>(new ResponMessage("not find"), HttpStatus.BAD_REQUEST);

        // kiem tra xem user hien tai co la user 2 hay khong trong db
        if(friend.get().getUsertwo().getUsername().equals(userDetailService.getCurrentUser().getUsername())){
            friend.get().setStatus(1);
            return new ResponseEntity<>(friendshipService.save(friend.get()),HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponMessage("khong the access"),HttpStatus.BAD_REQUEST);

    }

    // khong chap nhan ket ban

//    @PostMapping("/cancel/{id}")
//    public ResponseEntity<?> cancelFriend(@PathVariable("id") Long id)
}
