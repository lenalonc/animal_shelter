package com.shelter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "adoption_item")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AdoptionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "adoption")
    private Adoption adoption;

    @ManyToOne
    @JoinColumn(name = "pet")
    private Pet pet;

    @Column(name = "home_adaptation")
    private int homeAdaptation;
}
