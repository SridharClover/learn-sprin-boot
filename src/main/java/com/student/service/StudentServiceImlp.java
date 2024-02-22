package com.student.service;

import com.student.entity.Student;
import com.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImlp implements StudentService{

    @Autowired
    StudentRepository studentRepository;
    @Override
    public Student createUser(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> fetchAllUser() {
        return studentRepository.findAll();
    }

    @Override
    public Student fetchSingleUser(String userId) {
        Optional<Student> user = studentRepository.findById(userId);
        return user.orElse(null);
    }

    @Override
    public Student updateUser(Student student, String userId) {
        Optional<Student> oldStudent = studentRepository.findById(userId);
        if(oldStudent.isPresent()){
            Student student1 = oldStudent.get();
            student1.setFirstName(student.getFirstName());
            student1.setLastName(student.getLastName());
            student1.setEmail(student.getEmail());
            student1.setPassword(student.getPassword());
            return studentRepository.save(student1);
        }else {
            return null;
        }
    }

    @Override
    public void deleteUser(String userId) {
        studentRepository.deleteById(userId);

    }
}
