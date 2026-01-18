package com.learning.javapersistanceapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Customer Entity - Demonstrates TABLE generation strategy
 * 
 * WHAT: Uses a dedicated table to store and generate IDs
 * WHEN: Need database-independent ID generation
 * WHY: Works across all databases, but slower than SEQUENCE/IDENTITY
 */
@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    
    /**
     * TABLE Strategy
     * WHAT: Creates a separate table (id_generator_table) to track IDs
     * WHY: 
     * - Database independent
     * - Can share sequence across multiple entities
     * - Full control over ID generation
     * DOWNSIDE: 
     * - Additional table required
     * - Slower than SEQUENCE/IDENTITY
     * - More database round trips
     * WHEN: 
     * - Need portability across different databases
     * - Want to share ID generator across entities
     */
    @Id
    @GeneratedValue(
        strategy = GenerationType.TABLE,
        generator = "customer_table_generator"
    )
    @TableGenerator(
        name = "customer_table_generator",
        table = "id_generator_table",  // Table storing sequences
        pkColumnName = "sequence_name", // Column identifying which sequence
        valueColumnName = "next_value", // Column storing next ID value
        pkColumnValue = "customer_seq", // Value identifying this sequence
        initialValue = 1,
        allocationSize = 10  // Pre-allocate 10 IDs
    )
    private Long id;
    
    @Column(name = "customer_code", unique = true, nullable = false)
    private String customerCode;
    
    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(length = 20)
    private String phone;
    
    /**
     * Embedded with custom column prefix
     * WHAT: All Address columns will have 'billing_' prefix
     * WHY: Distinguish between multiple embedded objects of same type
     */
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "billing_street")),
        @AttributeOverride(name = "city", column = @Column(name = "billing_city")),
        @AttributeOverride(name = "state", column = @Column(name = "billing_state")),
        @AttributeOverride(name = "postalCode", column = @Column(name = "billing_postal_code")),
        @AttributeOverride(name = "country", column = @Column(name = "billing_country"))
    })
    private Address billingAddress;
    
    /**
     * Second embedded Address with different prefix
     * WHAT: Same Address type, different column names
     */
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "shipping_street")),
        @AttributeOverride(name = "city", column = @Column(name = "shipping_city")),
        @AttributeOverride(name = "state", column = @Column(name = "shipping_state")),
        @AttributeOverride(name = "postalCode", column = @Column(name = "shipping_postal_code")),
        @AttributeOverride(name = "country", column = @Column(name = "shipping_country"))
    })
    private Address shippingAddress;
    
    @Column(name = "registration_date")
    @Builder.Default
    private LocalDateTime registrationDate = LocalDateTime.now();
    
    @Column(name = "is_premium")
    @Builder.Default
    private Boolean premium = false;
}
