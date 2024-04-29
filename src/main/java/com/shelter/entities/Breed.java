package com.shelter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "breed")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Breed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "breed_name")
    private String breed;

    @ManyToOne
    @JoinColumn(name = "animal")
    private Animal animal;

}
