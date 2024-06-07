package com.shelter.entities;

import com.shelter.dtos.CreateUserDTO;
import com.shelter.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private UserDTO user;
}
