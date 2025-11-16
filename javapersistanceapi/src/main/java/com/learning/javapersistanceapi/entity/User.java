package com.learning.javapersistanceapi.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Sample User entity to demonstrate JPA basics
 * This class represents a table named 'users' in the database
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column
    private Integer age;
    
    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;
}
