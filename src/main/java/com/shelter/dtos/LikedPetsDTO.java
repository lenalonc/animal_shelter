package com.shelter.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikedPetsDTO {

    private Long id;

    private OwnerDTO owner;

    private PetDTO pet;
}
