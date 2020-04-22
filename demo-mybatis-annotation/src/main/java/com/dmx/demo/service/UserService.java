package com.dmx.demo.service;

import com.dmx.demo.mapper.UserMapper;
import com.dmx.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Matthew
 * Create Time: 2019-07-17 09:18
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getUserList() {
        return userMapper.getUserList();
    }
}