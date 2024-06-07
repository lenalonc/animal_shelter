package com.shelter.controller;

import com.shelter.dtos.LikedPetsDTO;
import com.shelter.dtos.OwnerDTO;
import com.shelter.dtos.CreateUserDTO;
import com.shelter.dtos.UpdateOwnerDTO;
import com.shelter.service.LikedPetsService;
import com.shelter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LikedPetsService likedPetsService;

    @GetMapping("owner/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("owner")
    public ResponseEntity<?> getAllOwners() {
        return ResponseEntity.ok(userService.getAllOwners());
    }

    @GetMapping("admin")
    public ResponseEntity<?> getAllAdmins() {
        return ResponseEntity.ok(userService.getAllAdmins());
    }

    @GetMapping("owner/{id}")
    public ResponseEntity<?> getOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getOwnerById(id));
    }

    @PostMapping("owner")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("owner/{id}")
    public ResponseEntity<?> updateOwner(@RequestBody UpdateOwnerDTO owner, @PathVariable Long id) {
        return ResponseEntity.ok(userService.updateOwner(owner, id));
    }

    @DeleteMapping("owner/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("owner/fields")
    public ResponseEntity<?> getFieldNamesForAddOwner() {
        return ResponseEntity.ok(userService.getFieldNamesForAddOwner());
    }

    @GetMapping("owner/liked/{id}")
    public ResponseEntity<?> getLikedPets(@PathVariable Long id) {
        return ResponseEntity.ok(likedPetsService.getAllLikedPetsForUser(id));
    }

    @PostMapping("owner/like")
    public ResponseEntity<?> likePet(@RequestBody LikedPetsDTO likedPets) {
        return ResponseEntity.ok(likedPetsService.likePet(likedPets));
    }

    @DeleteMapping("owner/{ownerId}/unlike/{petId}")
    public ResponseEntity<?> unlikePet(@PathVariable Long ownerId, @PathVariable Long petId) {
        likedPetsService.unlikePet(ownerId, petId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("owner/{ownerId}/pet/{petId}")
    public ResponseEntity<?> isPetLiked(@PathVariable Long ownerId, @PathVariable Long petId) {
        return ResponseEntity.ok(likedPetsService.isPetLiked(petId, ownerId));
    }

    @GetMapping("owner/email/{email}")
    public ResponseEntity<?> uniqueEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.uniqueEmail(email));
    }

    @GetMapping("owner/username/{username}")
    public ResponseEntity<?> uniqueUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.uniqueUsername(username));
    }

}
