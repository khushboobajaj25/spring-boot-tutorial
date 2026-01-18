package com.learning.javapersistanceapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Product Entity - Demonstrates various JPA mapping annotations
 * 
 * WHAT: Shows @Entity, @Table, @Column configurations
 * WHY: Control table name, column properties, constraints
 * WHEN: Use when you need custom table/column configurations
 */
@Entity
@Table(
    name = "products",  // Custom table name (default would be "Product")
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_product_code",
            columnNames = {"product_code"}
        )
    },
    indexes = {
        @Index(name = "idx_product_name", columnList = "name"),
        @Index(name = "idx_product_category", columnList = "category")
    }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    
    /**
     * PRIMARY KEY with IDENTITY strategy
     * WHAT: Auto-increment by database
     * WHEN: MySQL, PostgreSQL, SQL Server
     * WHY: Simple, database handles ID generation
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * Column with custom properties
     * WHAT: Controls column name, length, nullability
     * WHY: Enforce constraints at database level
     */
    @Column(
        name = "product_code",
        length = 50,
        nullable = false,
        unique = true,
        updatable = false  // Cannot be updated after creation
    )
    private String productCode;
    
    /**
     * Simple column mapping
     * WHAT: Column name matches field name by default
     * WHEN: Field name is already good for database
     */
    @Column(nullable = false, length = 200)
    private String name;
    
    /**
     * Column with custom name
     * WHAT: Java field 'description' -> DB column 'product_description'
     * WHY: Database naming conventions differ from Java
     */
    @Column(name = "product_description", columnDefinition = "TEXT")
    private String description;
    
    /**
     * Numeric column with precision and scale
     * WHAT: precision=10 (total digits), scale=2 (decimal places)
     * WHEN: Storing money, prices (e.g., 99999999.99)
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    /**
     * Integer column
     */
    @Column(nullable = false)
    @Builder.Default
    private Integer stockQuantity = 0;
    
    /**
     * Enum field with STRING mapping
     * WHAT: Stores enum name as VARCHAR in database
     * WHY: Readable in database, safer than ORDINAL
     * WHEN: Always prefer STRING over ORDINAL
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    @Builder.Default
    private ProductCategory category = ProductCategory.GENERAL;
    
    /**
     * Enum with ORDINAL mapping (NOT RECOMMENDED!)
     * WHAT: Stores enum position (0, 1, 2) as INTEGER
     * WHY: Dangerous - adding new enum values breaks existing data
     * WHEN: Avoid in production code
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_code")
    @Builder.Default
    private ProductStatus status = ProductStatus.ACTIVE;
    
    /**
     * Date field
     * WHAT: Stores LocalDate (date only, no time)
     * WHEN: Birth dates, manufacturing dates
     */
    @Column(name = "manufacture_date")
    private LocalDate manufactureDate;
    
    /**
     * DateTime field
     * WHAT: Stores LocalDateTime (date + time)
     * WHEN: Created/updated timestamps
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    
    /**
     * Transient field - NOT persisted to database
     * WHAT: Calculated/temporary field, exists only in memory
     * WHY: Derived data, runtime calculations
     * WHEN: Fields that shouldn't be stored
     */
    @Transient
    private BigDecimal discountedPrice;
    
    /**
     * Boolean field
     * WHAT: Stores true/false as BOOLEAN (or TINYINT in MySQL)
     */
    @Column(name = "is_featured")
    @Builder.Default
    private Boolean featured = false;
    
    /**
     * Calculate discounted price (transient field example)
     */
    public BigDecimal getDiscountedPrice() {
        if (discountedPrice == null && price != null) {
            // 10% discount example
            discountedPrice = price.multiply(BigDecimal.valueOf(0.9));
        }
        return discountedPrice;
    }
}
