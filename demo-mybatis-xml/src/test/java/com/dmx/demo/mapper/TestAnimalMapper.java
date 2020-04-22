package com.dmx.demo.mapper;

import com.dmx.demo.Application;
import com.dmx.demo.model.Animal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Matthew
 * Create Time: 2019-07-18 21:30
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAnimalMapper {
    @Autowired
    private AnimalMapper animalMapper;

    @Test
    public void testGet() {
        List<Animal> animalList = animalMapper.getAnimalList();
        Assert.isTrue(!CollectionUtils.isEmpty(animalList), "查询失败");
        animalList.stream().peek(System.out::println).count();
    }
}