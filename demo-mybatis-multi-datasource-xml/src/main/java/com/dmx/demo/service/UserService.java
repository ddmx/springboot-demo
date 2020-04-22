package com.dmx.demo.service;

import com.dmx.demo.mapper.test1.Test1Mapper;
import com.dmx.demo.mapper.test2.Test2Mapper;
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
    private Test1Mapper test1Mapper;

    @Autowired
    private Test2Mapper test2Mapper;

    public List<User> getUser1List() {
        return test1Mapper.getUserList();
    }

    public List<User> getUser2List() {
        return test2Mapper.getUserList();
    }
}