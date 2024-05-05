package com.shelter.repository;

import com.shelter.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepossitory extends JpaRepository<Animal, Long> {
}
