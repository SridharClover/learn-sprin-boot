package com.institution.institution.service;

import com.institution.institution.entity.Student;
import com.institution.institution.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
        Optional<Student> user = studentRepository.findById(Long.valueOf(userId));
        return user.orElse(null);
    }

    @Override
    public Student updateUser(Student student, String userId) {
        Optional<Student> oldStudent = studentRepository.findById(Long.valueOf(userId));
        if(oldStudent.isPresent()){
            Student student1 = oldStudent.get();
            if(Objects.nonNull(student.getFirstName()) && !"".equalsIgnoreCase(student.getFirstName())){
                student1.setFirstName(student.getFirstName());
            }
            if(Objects.nonNull(student.getLastName()) && !"".equalsIgnoreCase(student.getLastName())){
                student1.setLastName(student.getLastName());
            }
            if(Objects.nonNull(student.getEmail()) && !"".equalsIgnoreCase(student.getEmail())){
                student1.setEmail(student.getEmail());
            }
            if(Objects.nonNull(student.getMobileNo()) && !"".equalsIgnoreCase(student.getMobileNo())){
                student1.setMobileNo(student.getMobileNo());
            }
            return studentRepository.save(student1);
        }else {
            return null;
        }
    }

    @Override
    public String deleteUser(String userId) {
        Optional<Student> student = studentRepository.findById(Long.valueOf(userId));
        if(student.isPresent()){
            studentRepository.deleteById(Long.valueOf(userId));
            return "Student Deleted";
        }else{
            return "No Student found";
        }
    }
}
