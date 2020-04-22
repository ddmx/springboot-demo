package com.dmx.demo.mapper;

import com.dmx.demo.Application;
import com.dmx.demo.model.User;
import com.dmx.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Matthew
 * Create Time: 2019-07-18 21:30
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUserService {
    @Autowired
    private UserService userService;

    @Before
    public void setup() {
    }


    @Test
    public void testGet() {
        List<User> userList = userService.getUserList();
        userList.stream().peek(System.out::println).count();
    }
}