package com.shelter.service;

import com.shelter.dtos.AdoptionDTO;
import com.shelter.entities.Adoption;
import com.shelter.entities.AdoptionItem;
import com.shelter.repository.AdoptionItemRepository;
import com.shelter.repository.AdoptionRepository;
import com.shelter.repository.PetRepository;
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
    PetRepository petRepository;

    @Autowired
    ModelMapper mapper;


    @Transactional
    @Override
    public AdoptionDTO createAdoption(AdoptionDTO adoptionDTO) {
        Adoption adoption = mapper.map(adoptionDTO, Adoption.class);
        Adoption finalAdoption = adoptionRepository.save(adoption);

        for (AdoptionItem ai : adoption.getPets()
        ) {
            ai.getPet().setAdopted(true);
            petRepository.save(ai.getPet());
        }

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
        List<Adoption> adoptions = adoptionRepository.findAll();
        for (Adoption adoption : adoptions
        ) {
            adoption.setPets(adoptionItemRepository.findByAdoption_Id(adoption.getId()));
        }
        return adoptions.stream().map(adoption -> mapper.map(adoption, AdoptionDTO.class)).toList();
    }


    @Override
    public void deleteAdoption(Long id) {
        Adoption adoption = adoptionRepository.findById(id).orElseThrow();
        for (AdoptionItem adoptionItem : adoption.getPets()
        ) {
            adoptionItem.getPet().setAdopted(false);
            petRepository.save(adoptionItem.getPet());
        }
        //should i delete every adoption item manually or should i leave to db, or put the annotation for cascade deleting
        adoptionRepository.deleteById(id);
    }

    @Override
    public AdoptionDTO getAdoptionById(Long id) {
        Adoption adoption = adoptionRepository.findById(id).orElseThrow();
        return mapper.map(adoption, AdoptionDTO.class);
    }
}
