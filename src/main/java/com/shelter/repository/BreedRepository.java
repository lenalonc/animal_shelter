package com.shelter.repository;

import com.shelter.entities.Animal;
import com.shelter.entities.Breed;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Long> {
    List<Breed> findByAnimal_Id(Long animal);
}
