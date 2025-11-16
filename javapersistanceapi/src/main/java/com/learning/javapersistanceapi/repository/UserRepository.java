package com.learning.javapersistanceapi.repository;

import com.learning.javapersistanceapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * User Repository - demonstrates Spring Data JPA basics
 * JpaRepository provides CRUD operations out of the box
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Spring Data JPA automatically implements these methods!
    // No need to write implementation
    
    Optional<User> findByEmail(String email);
    
    List<User> findByIsActive(Boolean isActive);
    
    List<User> findByAgeGreaterThan(Integer age);
    
    List<User> findByNameContainingIgnoreCase(String name);
}
