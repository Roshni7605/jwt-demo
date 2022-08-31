package com.springpractice.jwt.service;

import com.springpractice.jwt.dto.LoginDTO;
import com.springpractice.jwt.entity.User;
import com.springpractice.jwt.repository.UserRepository;
import com.springpractice.jwt.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private TokenUtility tokenUtility;

    public User registerUser(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public String userLogin(LoginDTO loginDTO){
        Optional<User> userMailId = getUserByEmail(loginDTO.getEmailId());
        String token = tokenUtility.createToken(userMailId.get().getUser_Id());
        return token;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmailId(email);
    }

    public List<User> getAllUsers(String token){
        Long id = tokenUtility.decodeToken(token);
        return userRepository.findAll();
    }

    public Optional<User> getUser(String token){
        Long id = tokenUtility.decodeToken(token);
        return userRepository.findById(id);
    }
}
