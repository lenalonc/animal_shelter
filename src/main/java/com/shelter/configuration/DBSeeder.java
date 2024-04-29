package com.shelter.configuration;

import com.shelter.entities.Animal;
import com.shelter.entities.Breed;
import com.shelter.service.BreedService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DBSeeder implements CommandLineRunner {


    @Autowired
    BreedService breedService;


    private void seedCatBreeds() {
        String[] catBreeds = {
                "Siamese",
                "Persian",
                "Maine Coon",
                "Ragdoll",
                "Bengal",
                "Scottish Fold",
                "Sphynx",
                "British Shorthair",
                "Abyssinian",
                "Birman",
                "Burmese",
                "Russian Blue",
                "American Shorthair",
                "Turkish Angora",
                "Himalayan",
                "Devon Rex",
                "Cornish Rex",
                "Manx",
                "American Bobtail",
                "Savannah",
                "Tabby",
                "Tuxedo",
                "Calico",
                "Tortoiseshell",
                "Ginger (Orange)",
                "Black",
                "White",
                "Grey",
                "Cream",
                "Tricolor"
        };

        Animal animal = Animal.builder().id(1L).build();
        for (String breedName : catBreeds) {

            Breed breed = Breed.builder()
                    .breed(breedName)
                    .animal(animal)
                    .build();

            breedService.createBreed(breed);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        // seedCatBreeds();
    }
}
