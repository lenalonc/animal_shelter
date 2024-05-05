package com.shelter.service;

import com.shelter.dtos.AnimalDTO;
import com.shelter.dtos.BreedDTO;
import com.shelter.entities.Breed;
import com.shelter.repository.BreedRepository;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedServiceImpl implements BreedService {

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public BreedDTO createBreed(Breed breed) {
        System.out.println(breed);
        return mapper.map(breedRepository.save(breed), BreedDTO.class);
    }

    @Override
    public List<BreedDTO> getCatBreeds() {
        List<Breed> catBreeds = breedRepository.findByAnimal_Id(1L);
        return catBreeds.stream().map(cat -> mapper.map(cat, BreedDTO.class)).toList();
    }

    @Override
    public List<BreedDTO> getDogBreeds() {
        List<Breed> dogBreeds = breedRepository.findByAnimal_Id(2L);
        return dogBreeds.stream().map(dog -> mapper.map(dog, BreedDTO.class)).toList();
    }
}
