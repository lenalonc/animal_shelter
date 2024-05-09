package com.shelter.controller;

import ch.qos.logback.core.model.Model;
import com.shelter.dtos.AdoptionDTO;
import com.shelter.service.AdoptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adoption")
public class AdoptionController {

    @Autowired
    AdoptionService adoptionService;

    @PostMapping()
    public ResponseEntity<?> createAdoption(@RequestBody AdoptionDTO adoption) {
        return ResponseEntity.ok(adoptionService.createAdoption(adoption));
    }

    @GetMapping()
    public ResponseEntity<?> getAdoptions() {
        return ResponseEntity.ok(adoptionService.getAllAdoptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdoptionById(@PathVariable Long id) {
        return ResponseEntity.ok(adoptionService.getAdoptionById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdoption(@PathVariable Long id){
        adoptionService.deleteAdoption(id);
        return ResponseEntity.ok().build();
    }


}
