package com.shelter.service;

import com.shelter.dtos.OwnerDTO;
import com.shelter.entities.Owner;

import java.util.List;

public interface OwnerService {

    public List<OwnerDTO> getAllOwners();

    public Owner getOwnerById(Long id);

    public Owner createOwner(OwnerDTO owner);

    public Owner updateOwner(OwnerDTO owner, Long id);

    public void deleteOwner(Long id);

    public List<String> getFieldNamesForAddOwner();
}
