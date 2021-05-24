package com.rajat.blogapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody User user){
        // create new user
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<User> getUser(@PathVariable String email){
        // return user with given email
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(email));
    }

    @PutMapping("/user/{email}")
    public ResponseEntity<String> updateUser(@RequestBody User user,@PathVariable String email){
        // update given user
        userService.updateUser(user,email);
        return ResponseEntity.status(HttpStatus.OK).body("updated user successfully");
    }

    @DeleteMapping("/user/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email){
        // delete user with given email
        userService.deleteUser(email);
        return ResponseEntity.status(HttpStatus.OK).body("deleted user successfully");
    }
}
