package com.shelter.service;

import com.shelter.dtos.PetCreateDTO;
import com.shelter.dtos.PetDTO;
import com.shelter.entities.Pet;
import com.shelter.exceptions.NotFoundException;
import com.shelter.repository.PetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    PetRepository petRepository;


    @Autowired
    ModelMapper mapper;

    @Override
    public Pet createPet(PetCreateDTO pet) {
        return petRepository.save(mapper.map(pet, Pet.class));
    }

    @Override
    public List<PetDTO> getAllPets() {
        List<Pet> pets = petRepository.findAll();
        return pets.stream().map(pet -> mapper.map(pet, PetDTO.class)).toList();
    }

    @Override
    public PetDTO getPetById(Long id) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new NotFoundException("Pet not found"));
        return mapper.map(pet, PetDTO.class);
    }

    @Override
    public PetDTO updatePet(Pet pet, Long id) {
        Pet p = petRepository.findById(id).orElseThrow(() -> new NotFoundException("Pet not found."));
        pet.setId(id);
        return mapper.map(petRepository.save(pet), PetDTO.class);
    }

    @Override
    public void deletePet(Long id) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new NotFoundException("Pet not found."));
        petRepository.deleteById(id);
    }

    @Override
    public List<PetDTO> getAllPetsForAdoption() {
        List<Pet> pets = petRepository.findByAdoptedFalse();
        return pets.stream().map(pet -> mapper.map(pet, PetDTO.class)).toList();
    }

    @Override
    public List<PetDTO> getAllAdoptedPets() {
        List<Pet> pets = petRepository.findByAdoptedTrue();
        return pets.stream().map(pet -> mapper.map(pet, PetDTO.class)).toList();
    }


    public List<String> getFieldNamesForAddPet() {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = PetCreateDTO.class.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (!fieldName.equals("id") && !fieldName.equals("adopted")) {
                fieldNames.add(fieldName);
            }
        }
        return fieldNames;
    }

    @Override
    public String savePicture(MultipartFile picture, String name) {
        if (picture.isEmpty()) {
            return "Picture is empty";
        }

        try {
            String uploadDir = "C:/Users/user/Desktop/diplomski/shelter-front/shelter-front/src/img/pets";

            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            byte[] bytes = picture.getBytes();
            Path filePath = Paths.get(uploadDir + '/' + name + ".jpg");
            System.out.println(filePath.toString());
            Files.write(filePath, bytes);

            return "Picture saved successfully";
        } catch (IOException e) {
            return "Failed to save picture: " + e.getMessage();
        }
    }

}
