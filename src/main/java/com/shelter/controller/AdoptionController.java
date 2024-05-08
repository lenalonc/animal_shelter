package com.shelter.controller;

import ch.qos.logback.core.model.Model;
import com.shelter.dtos.AdoptionDTO;
import com.shelter.service.AdoptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adoption")
public class AdoptionController {

    @Autowired
    AdoptionService adoptionService;

    @PostMapping()
    public ResponseEntity<?> createAdoption(@RequestBody AdoptionDTO adoption) {
        return ResponseEntity.ok(adoptionService.createAdoption(adoption));
    }


}
