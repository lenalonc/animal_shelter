package com.shelter.controller;

import com.shelter.dtos.OwnerCreateDTO;
import com.shelter.dtos.PetCreateDTO;
import com.shelter.entities.Pet;
import com.shelter.service.OwnerService;
import com.shelter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping()
    public ResponseEntity<?> getAllOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.getOwnerById(id));
    }

    @PostMapping()
    public ResponseEntity<?> createOwner(@RequestBody OwnerCreateDTO owner) {
        return ResponseEntity.ok(ownerService.createOwner(owner));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOwner(@RequestBody OwnerCreateDTO owner, @PathVariable Long id) {
        return ResponseEntity.ok(ownerService.updateOwner(owner, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fields")
    public ResponseEntity<?> getFieldNamesForAddOwner() {
        return ResponseEntity.ok(ownerService.getFieldNamesForAddOwner());
    }


}
