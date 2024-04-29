package com.shelter.repository;

import com.shelter.entities.AdoptionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionItemRepository extends JpaRepository<AdoptionItem, Long> {
}
