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
public class UserController {
    @Autowired
    UserService studentService;

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User student){
        return studentService.createUser(student);
    }

    @GetMapping("/student")
    public List listAllUser(){
        return studentService.fetchAllUser();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> listSingleUser(@PathVariable("userId") String studentId){
        User student = studentService.fetchSingleUser(studentId);
        if(student == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@Valid @RequestBody User student, @PathVariable("userId") String studentId){
        return studentService.updateUser(student,studentId);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable("userId") String studentId){
         studentService.deleteUser(studentId);
    }

}
