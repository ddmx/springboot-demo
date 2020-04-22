package com.dmx.demo.controller;

import com.dmx.demo.model.Animal;
import com.dmx.demo.service.AnimalService;
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
    private AnimalService animalService;

    @GetMapping("/animals")
    public List<Animal> getUserList() {
        return animalService.getAnimalList();
    }
}