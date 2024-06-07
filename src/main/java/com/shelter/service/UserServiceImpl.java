package com.shelter.service;

import com.shelter.dtos.AdministratorDTO;
import com.shelter.dtos.OwnerDTO;
import com.shelter.dtos.CreateUserDTO;
import com.shelter.dtos.UpdateOwnerDTO;
import com.shelter.entities.User;
import com.shelter.exceptions.ForbiddenException;
import com.shelter.exceptions.NotFoundException;
import com.shelter.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<OwnerDTO> getAllOwners() {
        List<User> owners = userRepository.findByRole("customer");
        return owners.stream().map(owner -> mapper.map(owner, OwnerDTO.class)).toList();
    }

    @Override
    public List<AdministratorDTO> getAllAdmins() {
        List<User> admins = userRepository.findByRole("admin");
        return admins.stream().map(admin -> mapper.map(admin, AdministratorDTO.class)).toList();
    }

    @Override
    public OwnerDTO getOwnerById(Long id) {
        User owner = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Owner not found."));
        return mapper.map(owner, OwnerDTO.class);
    }

    @Override
    public OwnerDTO createUser(CreateUserDTO owner) {
        if (userRepository.findByUsername(owner.getUsername()).isPresent()) {
            throw new ForbiddenException("Username is already taken.");
        }
        if (userRepository.findByEmail(owner.getEmail()).isPresent()) {
            throw new ForbiddenException("Email is already taken.");
        }
        return mapper.map(userRepository.save(mapper.map(owner, User.class)), OwnerDTO.class);
    }

    @Override
    public OwnerDTO updateOwner(UpdateOwnerDTO owner, Long id) {
        User found = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Owner not found."));
        if (!owner.getUsername().equals(found.getUsername()) && userRepository.findByUsername(owner.getUsername()).isPresent()) {
            throw new ForbiddenException("Username is already taken.");
        }

        if (!owner.getEmail().equals(found.getEmail()) && userRepository.findByEmail(owner.getEmail()).isPresent()) {
            throw new ForbiddenException("Email is already taken.");
        }
        found.setFirstname(owner.getFirstname());
        found.setLastname(owner.getLastname());
        found.setEmail(owner.getEmail());
        found.setUsername(owner.getUsername());
        found.setRole(owner.getRole());
        return mapper.map(userRepository.save(found), OwnerDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found."));
        userRepository.deleteById(id);
    }


    @Override
    public List<String> getFieldNamesForAddOwner() {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (!fieldName.equals("adoptions") && !fieldName.equals("id") && !fieldName.equals("role")) {
                fieldNames.add(fieldName);
            }
        }
        return fieldNames;
    }

    @Override
    public boolean uniqueEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean uniqueUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return false;
        }
        return true;
    }
}
