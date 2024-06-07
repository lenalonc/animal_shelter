package com.shelter.repository;

import com.shelter.dtos.LikedPetsDTO;
import com.shelter.entities.LikedPets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikedPetsRepository extends JpaRepository<LikedPets, Long> {
    Optional<List<LikedPets>> findAllByOwner_IdAndPet_AdoptedFalse(Long id);

    boolean existsByOwner_IdAndPet_Id(Long ownerId, Long petId);

    Optional<LikedPets> findByOwner_IdAndPet_Id(Long ownerId, Long petId);
}
