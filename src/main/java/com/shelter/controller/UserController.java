package com.shelter.controller;

import com.shelter.dtos.OwnerDTO;
import com.shelter.dtos.UserDTO;
import com.shelter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("owner/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("owner")
    public ResponseEntity<?> getAllOwners() {
        return ResponseEntity.ok(userService.getAllOwners());
    }

    @GetMapping("owner/{id}")
    public ResponseEntity<?> getOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getOwnerById(id));
    }

    @PostMapping("owner")
    public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("owner/{id}")
    public ResponseEntity<?> updateOwner(@RequestBody OwnerDTO owner, @PathVariable Long id) {
        return ResponseEntity.ok(userService.updateOwner(owner, id));
    }

    @DeleteMapping("owner/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable Long id) {
        userService.deleteOwner(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("owner/fields")
    public ResponseEntity<?> getFieldNamesForAddOwner() {
        return ResponseEntity.ok(userService.getFieldNamesForAddOwner());
    }


}
