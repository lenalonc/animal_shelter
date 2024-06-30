package com.shelter.service;

import com.shelter.dtos.AnimalDTO;
import com.shelter.entities.Animal;
import com.shelter.repository.AnimalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalRepository animalRepossitory;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<AnimalDTO> getAnimalTypes() {
        List<Animal> animals = animalRepossitory.findAll();
        return animals.stream().map(animal -> mapper.map(animal, AnimalDTO.class)).toList();
    }
}
