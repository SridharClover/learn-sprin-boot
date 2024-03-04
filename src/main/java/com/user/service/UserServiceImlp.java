package com.user.service;

import com.user.api.request.LoginRequest;
import com.user.api.response.SuccessResponse;
import com.user.entity.User;
import com.user.repository.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImlp implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public User createUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        try {
            return userRepository.save(user);
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.CONFLICT,ex.getMessage());
        }
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
    public User checkUserLogin(LoginRequest loginRequest) {

//        if(!credentials.contains("Basic ")){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid Login Credentials");
//        }else {
//            String pair = new String(Base64.decodeBase64(credentials.substring(6)));
//            if(!pair.contains(":")){
//                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid Login Credentials");
//            }else {
//                String userName = pair.split(":")[0];
//                String password = pair.split(":")[1];
//                User user = userRepository.findByEmail(userName).orElseThrow( () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid Login Credentials"));
//                if (user != null) {
//                    String storedPassword = user.getPassword();
//                    if (passwordEncoder.matches(password, storedPassword)) {
//                        return SuccessResponse.builder()
//                                .message("Login Successful")
//                                .build();
//                    }
//                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid Login Credentials");
//                }
//                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid Login Credentials");
//            }
//        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        return userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();

    }
}
