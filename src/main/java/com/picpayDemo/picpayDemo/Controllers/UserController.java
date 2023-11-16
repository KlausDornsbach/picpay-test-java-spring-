package com.picpayDemo.picpayDemo.Controllers;

import com.picpayDemo.picpayDemo.DTOs.UserDTO;
import com.picpayDemo.picpayDemo.Domain.User;
import com.picpayDemo.picpayDemo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User user = new User(userDTO);
        userService.saveUserToDB(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() throws Exception {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
