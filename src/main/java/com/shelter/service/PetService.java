package com.shelter.service;

import com.shelter.dtos.PetCreateDTO;
import com.shelter.dtos.PetDTO;
import com.shelter.entities.Pet;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PetService {

    public Pet createPet(PetCreateDTO pet);

    public List<PetDTO> getAllPets();

    public PetDTO getPetById(Long id);

    public PetDTO updatePet(Pet pet, Long id);

    public void deletePet(Long id);

    public List<PetDTO> getPetsForAdoption();

    public List<PetDTO> getAdoptedPets();

    public List<String> getFieldNamesForAddPet();

    String savePicture(MultipartFile picture, String name);

}
