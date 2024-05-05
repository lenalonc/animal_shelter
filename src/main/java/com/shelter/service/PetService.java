package com.shelter.service;

import com.shelter.dtos.PetCreateDTO;
import com.shelter.dtos.PetDTO;
import com.shelter.entities.Pet;

import java.util.List;

public interface PetService {

    public Pet createPet(PetCreateDTO pet);

    public List<PetDTO> getAllPets();

    public PetDTO getPetById(Long id);

    public Pet updatePet(Pet pet, Long id);

    public void deletePet(Long id);

    public List<Pet> getPetsForAdoption();

    public List<Pet> getAdoptedPets();

    public List<String> getFieldNamesForAddPet();

}
