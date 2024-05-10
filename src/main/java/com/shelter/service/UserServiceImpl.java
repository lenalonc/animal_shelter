package com.shelter.service;

import com.shelter.dtos.OwnerDTO;
import com.shelter.dtos.UserDTO;
import com.shelter.entities.User;
import com.shelter.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.reflect.Field;


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
    public OwnerDTO getOwnerById(Long id) {
        User owner = userRepository.findById(id).orElseThrow();
        return mapper.map(owner, OwnerDTO.class);
    }

    @Override
    public OwnerDTO createUser(UserDTO owner) {
        return mapper.map(userRepository.save(mapper.map(owner, User.class)), OwnerDTO.class);
    }

    @Override
    public OwnerDTO updateOwner(OwnerDTO owner, Long id) {
        owner.setId(id);
        return mapper.map(userRepository.save(mapper.map(owner, User.class)), OwnerDTO.class);

    }

    @Override
    public void deleteOwner(Long id) {
        User owner = userRepository.findById(id).orElseThrow();
        if (!owner.getRole().equals("customer")) {
            //baci gresku
        }
        userRepository.deleteById(id);
    }


    @Override
    public List<String> getFieldNamesForAddOwner() {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = OwnerDTO.class.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (!fieldName.equals("adoptions") && !fieldName.equals("id")) {
                fieldNames.add(fieldName);
            }
        }
        return fieldNames;
    }
}
