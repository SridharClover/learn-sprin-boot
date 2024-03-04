package com.user.controller;

import com.user.api.request.LoginRequest;
import com.user.api.response.LoginResponse;
import com.user.entity.User;
import com.user.service.JwtService;
import com.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;

    @PostMapping("/signup")
    public User createUser(@Valid @RequestBody User student){
        return userService.createUser(student);
    }

    @GetMapping()
    public List<User> listAllUser(){
        return userService.fetchAllUser();
    }

    @GetMapping("/{userId}")
    public User listSingleUser(@PathVariable("userId") String studentId){
        return  userService.fetchSingleUser(studentId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@Valid @RequestBody User student, @PathVariable("userId") String studentId){
        return userService.updateUser(student,studentId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") String studentId){
         userService.deleteUser(studentId);
    }

    @PostMapping("/login")
    public LoginResponse userLogin(@RequestBody LoginRequest loginRequest){
        User authnticatedUser = userService.checkUserLogin(loginRequest);
        return LoginResponse.builder()
                .token(jwtService.generateToken(authnticatedUser))
                .expiresIn(jwtService.getExpirationTime())
                .build();
    }

}
