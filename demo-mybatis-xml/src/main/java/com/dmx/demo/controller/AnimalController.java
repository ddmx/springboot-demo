package com.dmx.demo.controller;

import com.dmx.demo.mapper.AnimalMapper;
import com.dmx.demo.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Matthew
 * Create Time: 2019-07-18 20:26
 */
@RestController
public class AnimalController {

    @Autowired
    private AnimalMapper animalMapper;

    @GetMapping("/animal")
    public List<Animal> getAnimalList() {
        return animalMapper.getAnimalList();
    }
}