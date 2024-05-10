package com.shelter.service;

import com.shelter.dtos.PetCreateDTO;
import com.shelter.dtos.PetDTO;
import com.shelter.entities.Pet;
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
        Pet pet = petRepository.findById(id).orElseThrow();
        return mapper.map(pet, PetDTO.class);
    }

    @Override
    public Pet updatePet(Pet pet, Long id) {
        pet.setId(id);
        return petRepository.save(pet);
    }

    @Override
    public void deletePet(Long id) {
        Pet pet = petRepository.findById(id).orElseThrow();
        petRepository.deleteById(id);
    }

    @Override
    public List<PetDTO> getPetsForAdoption() {
        List<Pet> pets = petRepository.findByAdoptedFalse();
        return pets.stream().map(pet -> mapper.map(pet, PetDTO.class)).toList();
    }

    @Override
    public List<PetDTO> getAdoptedPets() {
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

        System.out.println(name + " name");

        try {
            // Specify the directory where you want to save the picture
            String uploadDir = "C:/Users/user/Desktop/diplomski/shelter-front/shelter-front/src/img/pets";

            // Create the directory if it doesn't exist
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the picture to the specified directory
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
