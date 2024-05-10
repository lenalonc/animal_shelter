package com.shelter.entities;

import com.shelter.dtos.AdministratorDTO;
import com.shelter.dtos.OwnerDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "adoption")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "admin")
    private User admin;

    @OneToMany(mappedBy = "adoption")
    private List<AdoptionItem> pets;

}
