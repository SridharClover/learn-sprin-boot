package com.student.service;

import com.student.entity.Student;

import java.util.List;

public interface StudentService {
    Student createUser(Student user);

    List<Student> fetchAllUser();

    Student fetchSingleUser(String userId);

    Student updateUser(Student user, String userId);

    void deleteUser(String userId);
}
