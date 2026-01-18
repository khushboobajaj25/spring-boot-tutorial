package com.learning.javapersistanceapi.entity;

/**
 * Product Status Enum - ORDINAL mapping (demonstration only)
 * WARNING: Don't use ORDINAL in production!
 * Adding new status between existing ones breaks data
 */
public enum ProductStatus {
    ACTIVE,      // Stored as 0
    INACTIVE,    // Stored as 1
    DISCONTINUED // Stored as 2,
}
