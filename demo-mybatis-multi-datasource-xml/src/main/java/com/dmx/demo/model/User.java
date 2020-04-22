package com.dmx.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Matthew
 * Create Time: 2019-07-17 09:16
 */
@Getter
@Setter
@ToString
public class User {
    private Integer id;
    private String userName;
    private String hobby;
    private Integer age;
}