package com.student.controller;

import com.student.entity.Student;
import com.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public Student createStudent(@Valid @RequestBody Student student){
        return studentService.createUser(student);
    }

    @GetMapping("/student")
    public List listAllStudent(){
        return studentService.fetchAllUser();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Student> listSingleStudent(@PathVariable("studentId") String studentId){
        Student student = studentService.fetchSingleUser(studentId);
        if(student == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @PutMapping("/student/{studentId}")
    public Student updateStudent(@Valid @RequestBody Student student, @PathVariable("studentId") String studentId){
        return studentService.updateUser(student,studentId);
    }

    @DeleteMapping("/student/{studentId}")
    public void deleteStudent(@PathVariable("studentId") String studentId){
         studentService.deleteUser(studentId);
    }

}
