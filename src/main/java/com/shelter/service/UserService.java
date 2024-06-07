package com.shelter.service;

import com.shelter.dtos.AdministratorDTO;
import com.shelter.dtos.OwnerDTO;
import com.shelter.dtos.CreateUserDTO;
import com.shelter.dtos.UpdateOwnerDTO;

import java.util.List;

public interface UserService {

    public List<OwnerDTO> getAllOwners();

    public List<AdministratorDTO> getAllAdmins();

    public OwnerDTO getOwnerById(Long id);

    public OwnerDTO createUser(CreateUserDTO owner);

    public OwnerDTO updateOwner(UpdateOwnerDTO owner, Long id);

    public void deleteUser(Long id);

    public List<String> getFieldNamesForAddOwner();

    public boolean uniqueEmail(String email);

    public boolean uniqueUsername(String username);
}
