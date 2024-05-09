package com.shelter.dtos;

import com.shelter.entities.Administrator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdoptionDTO {

    private Long id;

    private LocalDate date = LocalDate.now();

    private OwnerDTO owner;

    private AdministratorDTO admin;

    private List<AdoptionItemDTO> pets;
}
