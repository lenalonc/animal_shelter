package com.shelter.service;

import com.shelter.dtos.LikedPetsDTO;
import com.shelter.dtos.OwnerDTO;
import com.shelter.dtos.PetDTO;
import com.shelter.entities.LikedPets;

import java.util.List;

public interface LikedPetsService {
    public List<LikedPetsDTO> getAllLikedPetsForUser(Long id);

    public LikedPetsDTO likePet(LikedPetsDTO likedPet);

    public void unlikePet(Long ownerId, Long petId);

    public boolean isPetLiked(Long petId, Long userId);
}
