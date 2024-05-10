package com.shelter.service;

import com.shelter.dtos.OwnerDTO;
import com.shelter.dtos.UserDTO;
import com.shelter.entities.User;

import java.util.List;

public interface UserService {

    public List<OwnerDTO> getAllOwners();

    public OwnerDTO getOwnerById(Long id);

    public OwnerDTO createUser(UserDTO owner);

    public OwnerDTO updateOwner(OwnerDTO owner, Long id);

    public void deleteOwner(Long id);

    public List<String> getFieldNamesForAddOwner();
}
