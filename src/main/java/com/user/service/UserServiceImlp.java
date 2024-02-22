package com.user.service;

import com.user.entity.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImlp implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public User createUser(User student) {
        return userRepository.save(student);
    }

    @Override
    public List<User> fetchAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User fetchSingleUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    @Override
    public User updateUser(User user, String userId) {
        Optional<User> oldUser = userRepository.findById(userId);
        if(oldUser.isPresent()){
            User user1 = oldUser.get();
            user1.setFirstName(user.getFirstName());
            user1.setLastName(user.getLastName());
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            return userRepository.save(user1);
        }else {
            return null;
        }
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);

    }
}
