package com.learning.javapersistanceapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Employee Entity - Demonstrates SEQUENCE generation strategy
 * 
 * WHAT: Uses database sequence for ID generation
 * WHEN: Oracle, PostgreSQL (supports sequences)
 * WHY: Better performance than IDENTITY for bulk inserts
 */
@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    
    /**
     * SEQUENCE Strategy
     * WHAT: Uses database sequence to generate IDs
     * WHY: 
     * - Pre-allocates IDs (better batch performance)
     * - Works with all databases (Hibernate creates table if needed)
     * - More control over ID generation
     * WHEN: 
     * - PostgreSQL, Oracle (native sequences)
     * - When you need batch inserts
     * - When you want control over allocation size
     */
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "employee_seq_generator"
    )
    @SequenceGenerator(
        name = "employee_seq_generator",
        sequenceName = "employee_sequence",
        initialValue = 1000,  // Start from 1000
        allocationSize = 50   // Pre-allocate 50 IDs at a time (performance boost)
    )
    private Long id;
    
    @Column(name = "employee_code", unique = true, nullable = false, length = 20)
    private String employeeCode;
    
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    /**
     * Embedded type demonstration
     * WHAT: Address is a reusable component, not a separate table
     * WHY: Group related fields, avoid duplication
     */
    @Embedded
    private Address address;
    
    @Column(name = "hire_date")
    private LocalDate hireDate;
    
    @Column(name = "is_active")
    @Builder.Default
    private Boolean active = true;
}
