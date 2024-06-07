package com.shelter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "liked_pets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikedPets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "pet")
    private Pet pet;
}
