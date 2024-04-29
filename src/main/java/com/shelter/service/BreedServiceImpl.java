package com.shelter.service;

import com.shelter.dtos.BreedDTO;
import com.shelter.entities.Breed;
import com.shelter.repository.BreedRepository;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
