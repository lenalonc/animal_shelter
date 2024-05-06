package com.shelter.dtos;

import com.shelter.entities.Animal;
import com.shelter.entities.Breed;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {

    private Long id;

    private String name;

    private int years;

    private int months;

    private String sex;

    private boolean vaccinated;

    private AnimalDTO animal;

    private BreedDTO breed;

    private String size;

    private double weight;

    private LocalDate dateOfArrival;

    private boolean sterilization;

    private String primaryColors;

    private boolean adopted;

}
