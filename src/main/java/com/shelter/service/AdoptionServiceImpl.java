package com.shelter.service;

import com.shelter.dtos.AdoptionDTO;
import com.shelter.entities.Adoption;
import com.shelter.repository.AdoptionItemRepository;
import com.shelter.repository.AdoptionRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionServiceImpl implements AdoptionService {

    @Autowired
    AdoptionRepository adoptionRepository;

    @Autowired
    AdoptionItemRepository adoptionItemRepository;

    @Autowired
    ModelMapper mapper;


    @Transactional
    @Override
    public AdoptionDTO createAdoption(AdoptionDTO adoptionDTO) {
        Adoption adoption = mapper.map(adoptionDTO, Adoption.class);
        System.out.println(adoption.getAdmin());
        Adoption finalAdoption = adoptionRepository.save(adoption);

        adoption.getPets().forEach(pet -> pet.setAdoption(adoption));
        adoptionItemRepository.saveAll(adoption.getPets());

        return mapper.map(adoption, AdoptionDTO.class);
    }

    @Override
    public AdoptionDTO updateAdoption(Adoption adoption, Long id) {
        adoption.setId(id);
        return mapper.map(adoptionRepository.save(adoption), AdoptionDTO.class);
    }

    @Override
    public List<AdoptionDTO> getAllAdoptions() {
        return null;
    }

    @Override
    public void deleteAdoption(Long id) {

    }
}
