package com.springpractice.jwt.controller;

import com.springpractice.jwt.dto.LoginDTO;
import com.springpractice.jwt.entity.User;
import com.springpractice.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param user
     * @return
     */
    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    /**
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public String userLogin(@RequestBody LoginDTO loginDTO){
        return userService.userLogin(loginDTO);
    }

    /**
     * @param token
     * @return
     */
    @GetMapping("/get-all-user")
    public List<User> getAllUsers(@RequestParam String token){
        return userService.getAllUsers(token);
    }

    /**
     * @param token
     * @return
     */
    @GetMapping("/get-user")
    public Optional<User> getUser(@RequestParam String token){
        return userService.getUser(token);
    }
}
