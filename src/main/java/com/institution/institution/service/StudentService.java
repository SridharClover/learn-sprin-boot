package com.institution.institution.service;

import com.institution.institution.entity.Student;

import java.util.List;

public interface StudentService {
    Student createUser(Student user);

    List<Student> fetchAllUser();

    Student fetchSingleUser(String userId);

    Student updateUser(Student user, String userId);

    String deleteUser(String userId);
}
