package com.user.service;

import com.user.entity.User;
import com.user.repository.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImlp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public User createUser(User user) {
        User user1 = userRepository.findByEmail(user.getEmail());
        if(user1!=null){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email Already Exist");
        }
        if(userRepository.findByMobileNo(user.getMobileNo()) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Mobile No Already Exist");
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    @Override
    public List<User> fetchAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User fetchSingleUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    @Override
    public User updateUser(User user, String userId) {
        User oldUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));;
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        return userRepository.save(oldUser);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public String checkUserLogin(String credentials) {
        String pair=new String(Base64.decodeBase64(credentials.substring(6)));
        String userName=pair.split(":")[0];
        String password=pair.split(":")[1];
        User user = userRepository.findByEmail(userName);
        if(user!=null){
            String storedPassword = user.getPassword();
            if(passwordEncoder.matches(password, storedPassword)){
                return "Login successful";
            }
            return "Invalid Password";
        }
        return "Invalid User Name";
    }
}
