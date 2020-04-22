package com.dmx.demo.service;

import com.dmx.demo.mapper.AnimalMapper;
import com.dmx.demo.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Matthew
 * Create Time: 2019-07-17 09:18
 */
@Service
public class AnimalService {
    @Autowired
    private AnimalMapper animalMapper;

    public List<Animal> getAnimalList() {
        return animalMapper.getAnimalList();
    }
}