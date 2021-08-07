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

import java.util.List;
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

        if(!userTwo.isPresent()) return new ResponseEntity<>(new ResponMessage("not find user"), HttpStatus.BAD_REQUEST);
        User user = userTwo.get();
        // check xem 2 thang kia co la ban hay khong
        Optional<Friend> friendCheck = friendshipService.findByUseroneAndAndUsertwo(userOne,user);
        if(friendCheck.isPresent()) return new ResponseEntity<>(new ResponMessage("not add friend"), HttpStatus.BAD_REQUEST);
        // ko phai tao moi
        Friend friend = new Friend(userOne,user);

        return new ResponseEntity<>(friendshipService.save(friend),HttpStatus.OK);

    }


    @PostMapping("/accept/{id}")
    public ResponseEntity<?> accessFriend(@PathVariable("id") Long id) {
        // lay lai friend

        Optional<Friend> friend = friendshipService.findById(id);
        if(!friend.isPresent()) return new ResponseEntity<>(new ResponMessage("not find"), HttpStatus.BAD_REQUEST);

        // kiem tra xem user hien tai co la user 2 hay khong trong db hay khong
        if(friend.get().getUsertwo().getUsername().equals(userDetailService.getCurrentUser().getUsername())) {
            if (friend.get().getStatus() == 0) {
                friend.get().setStatus(1);
                friendshipService.save(friend.get());
                return new ResponseEntity<>(new ResponMessage("ket ban thanh cong"), HttpStatus.OK);
            }

            if (friend.get().getStatus() == 1) return new ResponseEntity<>(new ResponMessage("da la ban"), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(new ResponMessage("khong the access"),HttpStatus.BAD_REQUEST);

    }

    // khong chap nhan ket ban

    @PostMapping("/cancel/{id}")
    public ResponseEntity<?> cancelFriend(@PathVariable("id") Long id){
        // lay ve friend

        Optional<Friend> friend = friendshipService.findById(id);
        if(!friend.isPresent()) return new ResponseEntity<>(new ResponMessage("not find"), HttpStatus.BAD_REQUEST);

        if(friend.get().getUsertwo().getUsername().equals(userDetailService.getCurrentUser().getUsername())){

//            if(friend.get())

            if(friend.get().getStatus() == 0 ||friend.get().getStatus() == 1  )  {
                friendshipService.delete(id);
                return new ResponseEntity<>(new ResponMessage("huy ket ban thanh cong"),HttpStatus.OK);
            }

            if(friend.get().getStatus() == 0 ||friend.get().getStatus() == 2  )  {
                return new ResponseEntity<>(new ResponMessage("chan ket ban khong the huy"),HttpStatus.BAD_REQUEST);
            }

//            friend.get().setStatus(1);
//            return new ResponseEntity<>(friendshipService.save(friend.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponMessage("khong the huy ket ban "),HttpStatus.BAD_REQUEST);
    }

    // chan ket ban

    @PostMapping("/block/{id}")
    public ResponseEntity<?> blockFriend(@PathVariable("id") Long id){
        // lay ve frend

        Optional<Friend> friend = friendshipService.findById(id);
        if(!friend.isPresent()) return new ResponseEntity<>(new ResponMessage("not find"), HttpStatus.BAD_REQUEST);

        if(friend.get().getUsertwo().getUsername().equals(userDetailService.getCurrentUser().getUsername())){

//            if(friend.get())

            if(friend.get().getStatus() == 0 ||friend.get().getStatus() == 1  )  {
                friend.get().setStatus(2);
                friendshipService.save(friend.get());
                return new ResponseEntity<>(new ResponMessage("chan thanh cong"),HttpStatus.OK);
            }

//            friend.get().setStatus(1);
//            return new ResponseEntity<>(friendshipService.save(friend.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponMessage("khong the chan ban "),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getFriend(){
        // lay user hien tai ra

        User currentUser = userDetailService.getCurrentUser();
        // lay ra danh sach ban
//            List a = (List) friendshipService.findAllFriendByStatus(currentUser.getId(),1);
        return new ResponseEntity<>(friendshipService.findAllFriendByStatus(currentUser.getId(),1),HttpStatus.OK);

    }
    @GetMapping("/listAccept")
    public ResponseEntity<?> getFriendAccept(){
        // lay user hien tai ra

        User currentUser = userDetailService.getCurrentUser();
        // lay ra danh sach ban
//            List a = (List) friendshipService.findAllFriendByStatus(currentUser.getId(),1);
        return new ResponseEntity<>(friendshipService.findAllFriendByUserOneStatus(currentUser.getId(),0),HttpStatus.OK);
    }
    // lay ra danh sach can accept
}
