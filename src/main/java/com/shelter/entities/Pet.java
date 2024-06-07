package com.shelter.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pet")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "years")
    private int years;

    @Column(name = "months")
    private int months;

    @Column(name = "sex")
    private String sex;

    @Column(name = "vaccinated")
    private boolean vaccinated;

    @ManyToOne
    @JoinColumn(name = "animal")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "breed")
    private Breed breed;

    @Column(name = "adopted")
    private boolean adopted;

    @Column(name = "size")
    private String size;

    @Column(name = "weight")
    private double weight;

    @Column(name = "date_of_arrival")
    private LocalDate dateOfArrival;

    @Column(name = "sterilization")
    private boolean sterilization;

    @Column(name = "primary_colors")
    private String primaryColors;

    @OneToMany(mappedBy = "pet")
    private List<AdoptionItem> adoptionItems;

}
