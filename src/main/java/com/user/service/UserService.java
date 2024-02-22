package com.user.service;

import com.user.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> fetchAllUser();

    User fetchSingleUser(String userId);

    User updateUser(User user, String userId);

    void deleteUser(String userId);
}
