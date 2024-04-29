package com.shelter.service;

import com.shelter.dtos.OwnerCreateDTO;
import com.shelter.entities.Owner;

import java.util.List;

public interface OwnerService {

    public List<Owner> getAllOwners();

    public Owner getOwnerById(Long id);

    public Owner createOwner(OwnerCreateDTO owner);

    public Owner updateOwner(OwnerCreateDTO owner, Long id);

    public void deleteOwner(Long id);

    public List<String> getFieldNamesForAddOwner();
}
