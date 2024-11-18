package org.example.miniforum.repository;

import org.example.miniforum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
