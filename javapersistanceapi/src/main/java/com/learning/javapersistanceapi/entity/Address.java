package com.learning.javapersistanceapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Address - Embeddable Type (NOT an Entity!)
 * 
 * WHAT: Reusable component that gets embedded into other entities
 * WHY: 
 * - Avoid duplication (multiple entities can have addresses)
 * - Better code organization
 * - All fields become columns in parent table
 * WHEN: 
 * - Common data structures (Address, Money, Name, etc.)
 * - Want to group related fields
 * - Don't need separate table for this data
 * 
 * NOTE: No @Entity, no @Id, no separate table!
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    
    @Column(name = "street_address", length = 200)
    private String street;
    
    @Column(name = "city", length = 100)
    private String city;
    
    @Column(name = "state", length = 50)
    private String state;
    
    @Column(name = "postal_code", length = 20)
    private String postalCode;
    
    @Column(name = "country", length = 50)
    private String country;
    
    /**
     * When embedded in Employee table, creates columns:
     * - street_address
     * - city
     * - state
     * - postal_code
     * - country
     */
}
