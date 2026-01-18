package com.learning.javapersistanceapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Department Entity - Demonstrates AUTO and UUID strategies
 * 
 * WHAT: Shows GenerationType.AUTO and UUID as primary key
 * WHY: Let JPA choose best strategy OR use UUID for distributed systems
 */
@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    
    /**
     * AUTO Strategy
     * WHAT: JPA/Hibernate decides the best strategy based on database
     * - MySQL/PostgreSQL: Usually IDENTITY
     * - Oracle: Usually SEQUENCE
     * - Others: May use TABLE
     * WHY: Database-agnostic code
     * WHEN: Don't care about specific strategy
     * 
     * NOTE: In Hibernate 5+, AUTO defaults to SEQUENCE (not IDENTITY)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // @GeneratedValue(
    //     strategy = GenerationType.TABLE,
    //     generator = "customer_table_generator"
    // )
    // private Long orderId;
    
    @Column(name = "department_code", unique = true, nullable = false, length = 10)
    private String departmentCode;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "is_active")
    @Builder.Default
    private Boolean active = true;
}

/**
 * Alternative: UUID Primary Key
 * WHY UUID:
 * - Globally unique (no coordination needed)
 * - Good for distributed systems
 * - Merge data from multiple sources
 * - Security (IDs not guessable)
 * 
 * DOWNSIDE:
 * - Larger storage (16 bytes vs 8 bytes for Long)
 * - Slower indexing
 * - Not human-friendly
 */
@Entity
@Table(name = "departments_with_uuid")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class DepartmentWithUUID {
    
    /**
     * UUID as Primary Key
     * WHAT: Universally Unique Identifier
     * WHEN: Distributed systems, microservices, data merging
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    private UUID id;
    
    @Column(nullable = false)
    private String name;
}
