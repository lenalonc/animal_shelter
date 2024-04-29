package com.shelter.service;

import com.shelter.dtos.BreedDTO;
import com.shelter.entities.Breed;

public interface BreedService {
    public BreedDTO createBreed(Breed breed);
}
