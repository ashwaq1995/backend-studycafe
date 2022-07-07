package com.studyCafeProject.Controller;

import com.studyCafeProject.DTO.Api;
import com.studyCafeProject.DTO.LoginDTO;
import com.studyCafeProject.Model.User;
import com.studyCafeProject.Service.UserService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    
    Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }




    //get all users
    @GetMapping("/login1/{username}")
    public ResponseEntity <User> getUserByUsername(@PathVariable String username){
        logger.info("get users");
        return ResponseEntity.status(200).body(userService.getUserByUsername(username));
    }





    //get all users
    @GetMapping("/all")
    public ResponseEntity <List<User>> getUsers(){
        logger.info("get users");
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<Api> addNewUser(@RequestBody @Valid User user){
        logger.info("add user");
        int addedUser = 0;
        addedUser = userService.addNewUser(user);

        switch (addedUser){
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("User is valid",400));
            case 1:
                return ResponseEntity.status(HttpStatus.OK).body(new Api("New User Added ! ",200));
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Email is Exists, try another email",400));
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Error Server",500));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser() {
        return ResponseEntity.status(200).body(new Api("Welcome back", 200));
    }



//    @PostMapping("/register")
//    public ResponseEntity<?> addNewUser(@RequestBody @Valid User user){
//        userService.addNewUser(user);
//        return ResponseEntity.status(200).body(new Api("New User added",200));
//    }

    @GetMapping("/user")
    public ResponseEntity<?> user(){
        return ResponseEntity.status(200).body(new Api("Hello User",200));
    }

    @GetMapping("/admin")
    public ResponseEntity<?> admin(){
        return ResponseEntity.status(200).body("Hello Admin");
    }

    //get user by id
    @GetMapping("/find/{userId}")
    public ResponseEntity<Api> getUsersById(@PathVariable Integer userId){
        logger.info("get user by his id");
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new Api("User is found",200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("user id is valid",400));
    }

    @GetMapping("/check/user/{id}")
    public ResponseEntity<Api> checkUserIsValid(@PathVariable Integer id){
        logger.info("check if user is valid");
        userService.checkUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Valid user",200));
    }

    @PutMapping("/edit/{userId}")
    ResponseEntity<Api> editUsers(@PathVariable Integer userId,@RequestBody User user) {
        logger.info("edit User By Id");
        Optional<User> edit = userService.editUserById(userId, user);
        if (edit.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new Api("Edit User "+ userId +" for : "+edit,200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("userId is valid",400));
    }

    @DeleteMapping("/delete/{userId}")
    ResponseEntity<Api> deleteUserById(@PathVariable Integer userId) {
        logger.info("remove User ");
        if (userService.removeUser(userId))
            return ResponseEntity.status(HttpStatus.OK).body(new Api("Delete User "+ userId,200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Not Found User "+ userId,200));
    }

}
