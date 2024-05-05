package com.shelter.service;

import com.shelter.dtos.AnimalDTO;
import com.shelter.dtos.PetCreateDTO;
import com.shelter.dtos.PetDTO;
import com.shelter.entities.Animal;
import com.shelter.entities.Owner;
import com.shelter.entities.Pet;
import com.shelter.repository.AnimalRepossitory;
import com.shelter.repository.PetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
//        return mapper.map(pet, PetDTO.class);
        return mapper.map(pet, PetDTO.class);
    }

    @Override
    public Pet updatePet(Pet pet, Long id) {
        pet.setId(id);
        return petRepository.save(pet);
    }

    @Override
    public void deletePet(Long id) {
        //petRepository.findById(id).orElseThrow(new Error("Pet doesnt exist."));
        petRepository.deleteById(id);
    }

    @Override
    public List<Pet> getPetsForAdoption() {
        return null;
    }

    @Override
    public List<Pet> getAdoptedPets() {
        return null;
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


}
