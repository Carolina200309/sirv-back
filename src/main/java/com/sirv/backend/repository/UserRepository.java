package com.sirv.backend.repository;

import com.sirv.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByNombre(String nombre);

    Optional<User> findByNombre(String nombre);


}