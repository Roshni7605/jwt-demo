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

    /**
     * To register user details in the database
     * @param user
     * @return
     */
    public User registerUser(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    /**
     * login using correct credential
     * After successful login generating a token
     * @param loginDTO
     * @return
     */
    public String userLogin(LoginDTO loginDTO){
        Optional<User> userMailId = getUserByEmail(loginDTO.getEmailId());
        String token = tokenUtility.createToken(userMailId.get().getUser_Id());
        return token;
    }

    /**
     * Get user by its email id
     * @param email
     * @return
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmailId(email);
    }

    /**
     * Retrieving user list using token
     * @param token
     * @return
     */
    public List<User> getAllUsers(String token){
        Long id = tokenUtility.decodeToken(token);
        return userRepository.findAll();
    }

    /**
     * Retrieving user by id using token
     * @param token
     * @return
     */
    public Optional<User> getUser(String token){
        Long id = tokenUtility.decodeToken(token);
        return userRepository.findById(id);
    }
}
