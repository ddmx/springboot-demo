package com.dmx.demo.controller;

import com.dmx.demo.model.User;
import com.dmx.demo.service.ExecutorCompletionServiceDemo;
import com.dmx.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/user1")
    public List<User> getUser1List() {
        return userService.getUser1List();
    }

    @GetMapping("/user1/page")
    public PageInfo<List> getUser1ListByPage(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> user1List = userService.getUser1List();
        PageInfo<List> pageInfo = new PageInfo(user1List);
        return pageInfo;
    }

    @GetMapping("/user2")
    public List<User> getUser2List() {
        return userService.getUser2List();
    }

    @GetMapping("/user2/page")
    public PageInfo<List> getUser2ListByPage(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> user1List = userService.getUser2List();
        PageInfo<List> pageInfo = new PageInfo(user1List);
        return pageInfo;
    }
}