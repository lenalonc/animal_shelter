package com.shelter.controller;

import com.shelter.dtos.PetCreateDTO;
import com.shelter.entities.Breed;
import com.shelter.entities.Pet;
import com.shelter.service.AnimalService;
import com.shelter.service.BreedService;
import com.shelter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private BreedService breedService;

    @Autowired
    private AnimalService animalService;

    @PostMapping()
    public ResponseEntity<?> createPet(@RequestBody PetCreateDTO pet) {
        return ResponseEntity.ok(petService.createPet(pet));
    }

    @GetMapping()
    public ResponseEntity<?> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPetById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePet(@RequestBody Pet pet, @PathVariable Long id) {
        return ResponseEntity.ok(petService.updatePet(pet, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/breed")
    public ResponseEntity<?> createBreed(@RequestBody Breed breed) {
        return ResponseEntity.ok(breedService.createBreed(breed));
    }

    @GetMapping("/fields")
    public ResponseEntity<?> getFieldNamesForAddPet() {
        return ResponseEntity.ok(petService.getFieldNamesForAddPet());
    }

    @GetMapping("/animals")
    public ResponseEntity<?> gteAnimals() {
        return ResponseEntity.ok(animalService.getAnimalTypes());
    }

    @GetMapping("/breeds/cat")
    public ResponseEntity<?> getCatBreeds() {
        return ResponseEntity.ok(breedService.getCatBreeds());
    }

    @GetMapping("/breeds/dog")
    public ResponseEntity<?> getDogBreeds() {
        return ResponseEntity.ok(breedService.getDogBreeds());
    }


}
