package com.shelter.service;

import com.shelter.dtos.BreedDTO;
import com.shelter.entities.Breed;

import java.util.List;

public interface BreedService {
    public BreedDTO createBreed(Breed breed);

    public List<BreedDTO> getCatBreeds();

    public List<BreedDTO> getDogBreeds();
}
