package com.user.controller;

import com.user.entity.User;
import com.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService studentService;

    @PostMapping()
    public User createUser(@Valid @RequestBody User student){
        return studentService.createUser(student);
    }

    @GetMapping()
    public List listAllUser(){
        return studentService.fetchAllUser();
    }

    @GetMapping("/{userId}")
    public User listSingleUser(@PathVariable("userId") String studentId){
        return  studentService.fetchSingleUser(studentId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@Valid @RequestBody User student, @PathVariable("userId") String studentId){
        return studentService.updateUser(student,studentId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") String studentId){
         studentService.deleteUser(studentId);
    }

    @PostMapping("/login")
    public String userLogin(@RequestHeader("Authorization") String authorization){
        return studentService.checkUserLogin(authorization);
    }

}
