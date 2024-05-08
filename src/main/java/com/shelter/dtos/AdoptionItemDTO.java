package com.shelter.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionItemDTO {
    private Long id;

    private AdoptionDTO adoption;

    private PetDTO pet;

    private String feedback;
}
