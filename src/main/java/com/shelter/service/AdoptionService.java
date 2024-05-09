package com.shelter.service;

import com.shelter.dtos.AdoptionDTO;
import com.shelter.entities.Adoption;

import java.util.List;

public interface AdoptionService {

    public AdoptionDTO createAdoption(AdoptionDTO adoptionDTO);

    public AdoptionDTO updateAdoption(Adoption adoption, Long id);

    public List<AdoptionDTO> getAllAdoptions();

    public void deleteAdoption(Long id);

    public AdoptionDTO getAdoptionById(Long id);

}
