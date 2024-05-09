package com.shelter.repository;

import com.shelter.entities.AdoptionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionItemRepository extends JpaRepository<AdoptionItem, Long> {
    List<AdoptionItem> findByAdoption_Id(Long adoption);
}
