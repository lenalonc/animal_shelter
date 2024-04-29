package com.shelter.dtos;

import com.shelter.entities.Animal;
import com.shelter.entities.Breed;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetCreateDTO {

    private Long id;

    private String name;

    @NotBlank
    private AnimalDTO animal;

    private BreedDTO breed;

    @NotBlank
    private String sex;

    private int years;

    private int months;

    @NotNull
    private boolean vaccinated;

    private String size;

    private int weight;

    private LocalDate dateOfArrival;

    private boolean sterilization;

    private String primaryColors;

    private boolean adopted = false;

}
