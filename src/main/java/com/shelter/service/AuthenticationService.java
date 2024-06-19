package com.shelter.service;

import com.shelter.dtos.EmailData;
import com.shelter.dtos.UserDTO;
import com.shelter.entities.AuthenticationResponse;
import com.shelter.entities.User;
import com.shelter.exceptions.BadRequestException;
import com.shelter.exceptions.NotFoundException;
import com.shelter.exceptions.ValidationException;
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
    private EmailService emailService;

    public AuthenticationResponse register(User request) throws Exception {
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            throw new ValidationException("Username is already taken");
        }
        existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new ValidationException("Email is already taken");
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

        emailService.sendEmail(new EmailData(user.getEmail(), "Registration Confirmation for Marlo shelter", "Dear " + user.getFirstname() + ",\n\n" + "Thank you for registering with Marlo Shelter! We are thrilled to have you join our community of animal lovers.\n\n" + "You can explore our available pets on our website and find your new furry friend. Don’t wait too long, as many pets are eagerly waiting for their forever homes.\n\n" + "Visit us at:\n\n" + "Marlo Shelter\n" + "1234 Paws and Claws Boulevard,\n" + "Barkington\n\n" + "Our visiting hours are:\n\n" + "Monday to Friday: 09:00 AM to 09:00 PM\n\n" + "We look forward to helping you find the perfect companion!\n\n" + "Best regards,\n\n" + "The Marlo Shelter Team\n\n"));

        return new AuthenticationResponse(token, mapper.map(user, UserDTO.class));
    }

    public AuthenticationResponse authenticate(User request) {
        User user = repository.findByUsername(request.getUsername()).orElseThrow(() -> new NotFoundException("Username not found."));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token, mapper.map(user, UserDTO.class));
    }

}