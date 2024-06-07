package com.shelter.service;

import com.shelter.dtos.UserDTO;
import com.shelter.entities.AuthenticationResponse;
import com.shelter.entities.User;
import com.shelter.exceptions.BadRequestException;
import com.shelter.exceptions.NotFoundException;
import com.shelter.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private ModelMapper mapper;
    private UserRepository userRepository;

    public AuthenticationResponse register(User request) throws Exception {
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            throw new BadRequestException("Username already taken");
        }
        existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new BadRequestException("Email already taken");
        }

        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("customer");
        user.setUsername(request.getUsername());

        user = repository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token, mapper.map(user, UserDTO.class));
    }

    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = repository.findByUsername(request.getUsername()).orElseThrow(() -> new NotFoundException("User with this username not found."));
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token, mapper.map(user, UserDTO.class));
    }

}