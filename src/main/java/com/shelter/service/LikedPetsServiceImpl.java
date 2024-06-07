package com.shelter.service;

import com.shelter.dtos.LikedPetsDTO;
import com.shelter.entities.LikedPets;
import com.shelter.entities.Pet;
import com.shelter.entities.User;
import com.shelter.exceptions.ForbiddenException;
import com.shelter.exceptions.NotFoundException;
import com.shelter.repository.LikedPetsRepository;
import com.shelter.repository.PetRepository;
import com.shelter.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikedPetsServiceImpl implements LikedPetsService {

    @Autowired
    private LikedPetsRepository likedPetsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<LikedPetsDTO> getAllLikedPetsForUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
        List<LikedPets> pets = likedPetsRepository.findAllByOwner_IdAndPet_AdoptedFalse(id).orElseThrow(() -> new NotFoundException("Pets not found based on given criteria."));
        return pets.stream().map(pet -> mapper.map(pet, LikedPetsDTO.class)).toList();
    }

    @Override
    public LikedPetsDTO likePet(LikedPetsDTO likedPet) {
        Pet pet = petRepository.findById(likedPet.getPet().getId()).orElseThrow(() -> new NotFoundException("Pet not found."));
        User owner = userRepository.findById(likedPet.getOwner().getId()).orElseThrow(() -> new NotFoundException("Owner not found."));
        LikedPets like = mapper.map(likedPet, LikedPets.class);
        Optional<LikedPets> liked = likedPetsRepository.findByOwner_IdAndPet_Id(owner.getId(), pet.getId());
        if (liked.isPresent()) {
            throw new ForbiddenException("This pet is already liked by this user.");
        }
        return mapper.map(likedPetsRepository.save(like), LikedPetsDTO.class);
    }

    @Override
    public void unlikePet(Long ownerId, Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new NotFoundException("Pet not found."));
        User owner = userRepository.findById(ownerId).orElseThrow(() -> new NotFoundException("Owner not found."));
        LikedPets likedPets = likedPetsRepository.findByOwner_IdAndPet_Id(ownerId, petId).orElseThrow(() -> new NotFoundException("This user did not like this pet."));
        likedPetsRepository.deleteById(likedPets.getId());
    }

    @Override
    public boolean isPetLiked(Long petId, Long userId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new NotFoundException("Pet not found."));
        User owner = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Owner not found."));
        return likedPetsRepository.existsByOwner_IdAndPet_Id(owner.getId(), pet.getId());
    }

}
