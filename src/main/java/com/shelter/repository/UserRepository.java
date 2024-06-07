package com.shelter.repository;

import com.shelter.entities.Pet;
import com.shelter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(String role);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

}
