package com.dmx.demo.mapper;

import com.dmx.demo.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM test")
    List<User> getUserList();
}
