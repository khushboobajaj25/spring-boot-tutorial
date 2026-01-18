# Entity Mapping - Complete Guide

## 📚 Overview

Entity Mapping is the **core of JPA** - it defines how Java objects translate to database tables. This guide covers all essential mapping techniques.

---

## 1. @Entity and @Table

### **WHAT:**
- `@Entity`: Marks a class as a JPA entity (database table)
- `@Table`: Customizes table properties (optional)

### **WHY:**
- Control table name, schema, constraints
- Add indexes for performance
- Define unique constraints

### **Example:**
```java
@Entity
@Table(
    name = "products",           // Custom table name
    schema = "public",           // Database schema
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_product_code",
            columnNames = {"product_code"}
        )
    },
    indexes = {
        @Index(name = "idx_product_name", columnList = "name")
    }
)
public class Product {
    // fields...
}
```

### **WHEN to use:**
- `@Entity`: **Always** (mandatory for JPA entities)
- `@Table`: When you need custom table name or constraints

---

## 2. Primary Key Generation Strategies

### **Strategy 1: IDENTITY** ⭐ Most Common

**WHAT:** Database auto-increments the ID  
**WHY:** Simple, database handles generation  
**WHEN:** MySQL, PostgreSQL, SQL Server  
**DOWNSIDE:** Can't batch inserts efficiently

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

**SQL Generated:**
```sql
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY  -- MySQL
    id BIGSERIAL PRIMARY KEY              -- PostgreSQL
)
```

---

### **Strategy 2: SEQUENCE** ⭐ Best for Batch Operations

**WHAT:** Uses database sequence  
**WHY:** Pre-allocates IDs, better performance  
**WHEN:** PostgreSQL, Oracle, batch inserts  

```java
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
@SequenceGenerator(
    name = "emp_seq",
    sequenceName = "employee_sequence",
    initialValue = 1000,    // Start from 1000
    allocationSize = 50     // Pre-allocate 50 IDs
)
private Long id;
```

**SQL Generated:**
```sql
CREATE SEQUENCE employee_sequence START WITH 1000 INCREMENT BY 50;
```

**Performance Benefit:**
- Fetches 50 IDs at once
- Reduces database round trips
- Better for bulk inserts

---

### **Strategy 3: TABLE** (Portable but Slow)

**WHAT:** Uses a table to store next ID  
**WHY:** Works on any database  
**WHEN:** Need database independence  
**DOWNSIDE:** Slowest strategy

```java
@Id
@GeneratedValue(strategy = GenerationType.TABLE, generator = "cust_gen")
@TableGenerator(
    name = "cust_gen",
    table = "id_generator_table",
    pkColumnName = "sequence_name",
    valueColumnName = "next_value",
    pkColumnValue = "customer_seq",
    allocationSize = 10
)
private Long id;
```

**SQL Generated:**
```sql
CREATE TABLE id_generator_table (
    sequence_name VARCHAR(255) PRIMARY KEY,
    next_value BIGINT
);
```

---

### **Strategy 4: AUTO** (Let Hibernate Decide)

**WHAT:** JPA chooses best strategy for your database  
**WHY:** Database-agnostic code  
**WHEN:** Don't care about specific strategy  

```java
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
```

**Hibernate Chooses:**
- MySQL → Usually IDENTITY
- PostgreSQL → Usually SEQUENCE
- Oracle → SEQUENCE

---

### **Strategy 5: UUID** (Distributed Systems)

**WHAT:** Universally Unique Identifier  
**WHY:** Globally unique, no coordination needed  
**WHEN:** Microservices, distributed systems  

```java
@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;
```

**Pros:**
- ✅ Globally unique
- ✅ Generate before insert
- ✅ Merge data from multiple sources

**Cons:**
- ❌ 16 bytes vs 8 bytes (Long)
- ❌ Slower indexing
- ❌ Not human-readable

---

## 3. Column Mapping

### **Basic @Column Properties:**

```java
@Column(
    name = "product_code",      // Column name in database
    length = 50,                // VARCHAR length
    nullable = false,           // NOT NULL constraint
    unique = true,              // UNIQUE constraint
    updatable = false,          // Cannot update after insert
    insertable = true,          // Can insert (default true)
    precision = 10,             // For BigDecimal (total digits)
    scale = 2,                  // For BigDecimal (decimal places)
    columnDefinition = "TEXT"   // Custom SQL type
)
private String productCode;
```

---

### **@Enumerated - Storing Enums**

#### **STRING Mapping** ⭐ RECOMMENDED

```java
@Enumerated(EnumType.STRING)
@Column(length = 20)
private ProductCategory category;

enum ProductCategory {
    ELECTRONICS,  // Stored as "ELECTRONICS"
    CLOTHING,     // Stored as "CLOTHING"
    FOOD          // Stored as "FOOD"
}
```

**WHY String is better:**
- ✅ Readable in database
- ✅ Safe to add new values
- ✅ Order doesn't matter
- ✅ Self-documenting

#### **ORDINAL Mapping** ❌ AVOID

```java
@Enumerated(EnumType.ORDINAL)
private ProductStatus status;

enum ProductStatus {
    ACTIVE,      // Stored as 0
    INACTIVE,    // Stored as 1
    DISCONTINUED // Stored as 2
}
```

**WHY avoid ORDINAL:**
- ❌ Adding new enum value breaks existing data
- ❌ Not readable in database
- ❌ Order-dependent

**Example of disaster:**
```java
// Original
enum Status { ACTIVE, INACTIVE }

// Later, you add:
enum Status { PENDING, ACTIVE, INACTIVE }
//            ^ New value shifts all others!
// ACTIVE was 0, now it's 1 - DATA CORRUPTION!
```

---

### **@Temporal - Date/Time Fields** (Legacy)

**NOTE:** With Java 8+, use `LocalDate`, `LocalDateTime` instead!

```java
// OLD WAY (Java 7 and below)
@Temporal(TemporalType.DATE)
private Date birthDate;

@Temporal(TemporalType.TIMESTAMP)
private Date createdAt;

// NEW WAY (Java 8+) - No @Temporal needed!
private LocalDate birthDate;        // Stores date only
private LocalDateTime createdAt;    // Stores date + time
private LocalTime workStartTime;    // Stores time only
```

---

### **@Transient - Exclude from Database**

**WHAT:** Field exists in Java but NOT in database  
**WHY:** Calculated fields, temporary data  
**WHEN:** Derived values, cache, runtime state  

```java
@Transient
private BigDecimal discountedPrice;  // Calculated, not stored

public BigDecimal getDiscountedPrice() {
    return price.multiply(BigDecimal.valueOf(0.9));
}
```

---

## 4. Embedded Types

### **@Embeddable + @Embedded**

**WHAT:** Group related fields into reusable component  
**WHY:** Avoid duplication, better organization  
**WHEN:** Address, Money, Name, etc.  

```java
// Reusable component (NOT an entity!)
@Embeddable
public class Address {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}

// Use in entities
@Entity
public class Employee {
    @Id
    private Long id;
    
    @Embedded
    private Address address;  // All Address fields added to employee table
}
```

**Database Table:**
```sql
CREATE TABLE employees (
    id BIGINT PRIMARY KEY,
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    postal_code VARCHAR(255),
    country VARCHAR(255)
);
```

---

### **Multiple Embedded Objects - @AttributeOverrides**

**Problem:** Two Address fields would clash!

```java
@Entity
public class Customer {
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "billing_street")),
        @AttributeOverride(name = "city", column = @Column(name = "billing_city"))
    })
    private Address billingAddress;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "shipping_street")),
        @AttributeOverride(name = "city", column = @Column(name = "shipping_city"))
    })
    private Address shippingAddress;
}
```

**Result:**
```sql
CREATE TABLE customers (
    id BIGINT PRIMARY KEY,
    billing_street VARCHAR(255),
    billing_city VARCHAR(255),
    shipping_street VARCHAR(255),
    shipping_city VARCHAR(255)
);
```

---

## 5. Large Objects (LOBs)

### **@Lob - Large Objects**

**WHAT:** Store large text or binary data  
**WHEN:** Files, images, long text  

#### **CLOB - Character Large Object**
```java
@Lob
@Column(columnDefinition = "TEXT")
private String longDescription;  // > 4000 characters
```

**Databases:**
- PostgreSQL: `TEXT`
- MySQL: `LONGTEXT`
- Oracle: `CLOB`

#### **BLOB - Binary Large Object**
```java
@Lob
private byte[] imageData;  // Binary data (images, PDFs)
```

**Databases:**
- PostgreSQL: `BYTEA`
- MySQL: `LONGBLOB`
- Oracle: `BLOB`

---

### **@Basic(fetch = LAZY) - Lazy Loading**

**WHAT:** Don't load field until accessed  
**WHY:** Performance - skip large data when not needed  
**WHEN:** Large binary/text data  

```java
@Lob
@Basic(fetch = FetchType.LAZY)
private byte[] largePdfFile;  // Not loaded by default
```

**Behavior:**
```java
// Query doesn't load largePdfFile
Document doc = documentRepository.findById(1L).get();

// Accessing triggers lazy load (requires open transaction!)
byte[] pdf = doc.getLargePdfFile();  // Now it loads
```

**⚠️ WARNING:** Can cause `LazyInitializationException` if accessed outside transaction!

---

## 📊 Strategy Comparison Table

| Strategy | Speed | Use Case | Database | Batch Insert |
|----------|-------|----------|----------|--------------|
| **IDENTITY** | Fast | Most common | MySQL, PostgreSQL | ❌ Slow |
| **SEQUENCE** | Fast | Best for batches | PostgreSQL, Oracle | ✅ Fast |
| **TABLE** | Slow | Portable | Any | ⚠️ Medium |
| **AUTO** | Varies | Database-agnostic | Any | Varies |
| **UUID** | Medium | Distributed systems | Any | ✅ Fast |

---

## 🎯 Best Practices

### **✅ DO:**
1. Use `@Enumerated(EnumType.STRING)` for enums
2. Use `LocalDate`/`LocalDateTime` instead of `Date`
3. Use `@Embedded` for reusable components
4. Use `SEQUENCE` for batch operations
5. Use `@Transient` for calculated fields
6. Use `LAZY` loading for large binary data

### **❌ DON'T:**
1. Use `@Enumerated(EnumType.ORDINAL)` in production
2. Store large files as BLOBs (use file paths instead)
3. Use `@Temporal` with Java 8+ date/time types
4. Use `TABLE` strategy unless necessary
5. Forget `@AttributeOverrides` with multiple embedded objects

---

## 🚀 Summary

**What You Learned:**
- ✅ `@Entity` and `@Table` configuration
- ✅ All 5 primary key generation strategies
- ✅ Column mapping with `@Column`, `@Enumerated`, `@Temporal`
- ✅ `@Transient` for non-persistent fields
- ✅ `@Embeddable` and `@Embedded` for reusable components
- ✅ `@Lob` for large objects
- ✅ `@Basic(fetch = LAZY)` for lazy loading

**When to Use What:**
- **Small app, MySQL**: Use `IDENTITY`
- **Large app, batch inserts**: Use `SEQUENCE`
- **Distributed system**: Use `UUID`
- **Need portability**: Use `AUTO` or `TABLE`
- **Common fields**: Use `@Embedded`
- **Large data**: Use `@Lob` with `LAZY`

---

**Ready to see it in action? Run the application!** 🎉
