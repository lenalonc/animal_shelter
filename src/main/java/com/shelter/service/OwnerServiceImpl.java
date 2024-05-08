package com.shelter.service;

import com.shelter.dtos.OwnerDTO;
import com.shelter.entities.Owner;
import com.shelter.repository.OwnerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.reflect.Field;


@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner getOwnerById(Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) {
            System.out.println("Stavi da se baca greska nOtFOund");
        }
        return owner.get();
    }

    @Override
    public Owner createOwner(OwnerDTO owner) {
        return ownerRepository.save(mapper.map(owner, Owner.class));
    }

    @Override
    public Owner updateOwner(OwnerDTO owner, Long id) {
        owner.setId(id);
        return ownerRepository.save(mapper.map(owner, Owner.class));
    }

    @Override
    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }


    @Override
    public List<String> getFieldNamesForAddOwner() {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = Owner.class.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (!fieldName.equals("adoptions") && !fieldName.equals("id")) {
                fieldNames.add(fieldName);
            }
        }
        return fieldNames;
    }
}
