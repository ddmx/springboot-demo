package com.dmx.demo.controller;

import com.dmx.demo.model.User;
import com.dmx.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Matthew
 * Create Time: 2019-07-18 20:26
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUserLists() {
        List<User> userList = userService.getUserList();
        return userList;
    }
}