package com.institution.institution.controller;

import com.institution.institution.entity.Student;
import com.institution.institution.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Student listSingleStudent(@PathVariable("studentId") String studentId){
        return studentService.fetchSingleUser(studentId);
    }

    @PutMapping("/student/{studentId}")
    public Student updateStudent(@RequestBody Student student, @PathVariable("studentId") String studentId){
        return studentService.updateUser(student,studentId);
    }

    @DeleteMapping("/student/{studentId}")
    public String deleteStudent(@PathVariable("studentId") String studentId){
        return studentService.deleteUser(studentId);
    }

}
