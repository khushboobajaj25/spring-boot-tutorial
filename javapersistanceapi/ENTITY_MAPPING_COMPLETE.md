# 🎓 Entity Mapping - Topic Complete!

## ✅ What We've Covered

You've just completed **Topic 3: Entity Mapping**, the absolute core of JPA! Here's everything you learned with practical, working examples.

---

## 📦 Entities Created

### 1. **Product** (IDENTITY Strategy)
- ✅ `GenerationType.IDENTITY` - Auto-increment ID
- ✅ Custom table name with `@Table`
- ✅ Unique constraints and indexes
- ✅ `@Column` with various configurations (length, nullable, unique, updatable)
- ✅ `BigDecimal` for prices (precision & scale)
- ✅ `@Enumerated(EnumType.STRING)` - ProductCategory
- ✅ `@Enumerated(EnumType.ORDINAL)` - ProductStatus (demo only!)
- ✅ `LocalDate` and `LocalDateTime` for dates
- ✅ `@Transient` for calculated fields
- ✅ Boolean fields

### 2. **Employee** (SEQUENCE Strategy)
- ✅ `GenerationType.SEQUENCE` - Database sequence
- ✅ `@SequenceGenerator` with initialValue and allocationSize
- ✅ `@Embedded` Address component
- ✅ Custom column names
- ✅ Email and code fields with constraints

### 3. **Customer** (TABLE Strategy)
- ✅ `GenerationType.TABLE` - ID generator table
- ✅ `@TableGenerator` configuration
- ✅ Multiple `@Embedded` objects (billing & shipping addresses)
- ✅ `@AttributeOverrides` to customize embedded field names
- ✅ Demonstrates column prefixing

### 4. **Document** (LOB Demonstration)
- ✅ `@Lob` for Character Large Objects (CLOB)
- ✅ `@Lob` for Binary Large Objects (BLOB)
- ✅ `@Basic(fetch = FetchType.LAZY)` for lazy loading
- ✅ Storing large text and binary data
- ✅ File metadata fields

### 5. **Department** (AUTO Strategy)
- ✅ `GenerationType.AUTO` - Let Hibernate choose
- ✅ Simple configuration
- ✅ Database-agnostic
- ✅ Bonus: UUID alternative example

### 6. **Address** (Embeddable Component)
- ✅ `@Embeddable` annotation
- ✅ Reusable across multiple entities
- ✅ No separate table
- ✅ Fields become columns in parent table

---

## 🔑 Key Concepts Mastered

### **Primary Key Strategies**

| Strategy | Best For | Pros | Cons |
|----------|----------|------|------|
| **IDENTITY** | MySQL, PostgreSQL, Simple apps | Easy, database-handled | Poor batch performance |
| **SEQUENCE** | PostgreSQL, Oracle, Batches | Best performance, pre-allocation | Requires sequence support |
| **TABLE** | Any database, Portability | Works everywhere | Slowest |
| **AUTO** | Don't care which | Database-agnostic | Less control |
| **UUID** | Distributed systems | Globally unique | Larger, slower |

### **Column Mapping Options**

```java
@Column(
    name = "custom_name",     // Database column name
    length = 50,              // VARCHAR length
    nullable = false,         // NOT NULL
    unique = true,            // UNIQUE constraint
    updatable = false,        // Cannot UPDATE
    insertable = true,        // Can INSERT
    precision = 10,           // BigDecimal total digits
    scale = 2,                // BigDecimal decimal places
    columnDefinition = "TEXT" // Custom SQL type
)
```

### **Enum Mapping**

**✅ ALWAYS Use STRING:**
```java
@Enumerated(EnumType.STRING)  // Stores "ACTIVE", "INACTIVE"
private Status status;
```

**❌ NEVER Use ORDINAL in Production:**
```java
@Enumerated(EnumType.ORDINAL)  // Stores 0, 1, 2 - DANGEROUS!
private Status status;
```

### **Date/Time Handling**

```java
// Modern way (Java 8+)
private LocalDate birthDate;        // Date only
private LocalDateTime createdAt;    // Date + Time
private LocalTime startTime;        // Time only

// Old way (avoid)
@Temporal(TemporalType.DATE)
private Date birthDate;
```

### **Embedded Types**

```java
// Define reusable component
@Embeddable
public class Address { ... }

// Use in entities
@Embedded
private Address address;

// Customize column names
@Embedded
@AttributeOverrides({
    @AttributeOverride(name = "street", column = @Column(name = "billing_street"))
})
private Address billingAddress;
```

### **Large Objects**

```java
// Text data
@Lob
@Column(columnDefinition = "TEXT")
private String longContent;  // CLOB

// Binary data
@Lob
private byte[] fileData;  // BLOB

// Lazy loading
@Lob
@Basic(fetch = FetchType.LAZY)
private byte[] largeFile;  // Only load when accessed
```

---

## 📁 Files Created

### **Entity Classes:**
1. `Product.java` - Complete column mapping demo
2. `Employee.java` - SEQUENCE strategy & embedded types
3. `Customer.java` - TABLE strategy & multiple embeddables
4. `Document.java` - LOB and lazy loading
5. `Department.java` - AUTO strategy & UUID
6. `Address.java` - Embeddable component
7. `ProductCategory.java` - Enum (STRING mapping)
8. `ProductStatus.java` - Enum (ORDINAL demo)

### **Repositories:**
1. `ProductRepository.java`
2. `EmployeeRepository.java`
3. `CustomerRepository.java`
4. `DocumentRepository.java`
5. `DepartmentRepository.java`

### **Demo:**
1. `EntityMappingDemoRunner.java` - Comprehensive demonstration

### **Documentation:**
1. `ENTITY_MAPPING_GUIDE.md` - Complete reference guide

---

## 🎯 Real-World Takeaways

### **Production Best Practices:**

1. **Primary Keys:**
   - Small apps → `IDENTITY`
   - Large apps with batches → `SEQUENCE`
   - Distributed systems → `UUID`

2. **Enums:**
   - ALWAYS use `EnumType.STRING`
   - Never use `ORDINAL` (breaks when enum order changes)

3. **Large Data:**
   - Use `@Lob` for < 1MB
   - Store file paths for > 1MB
   - Use `LAZY` loading for optional large fields

4. **Embedded Types:**
   - Use for Address, Money, Name, etc.
   - Reduces code duplication
   - Better organization

5. **Dates:**
   - Use `LocalDate`, `LocalDateTime`, `LocalTime`
   - Avoid old `java.util.Date`

---

## 🧪 What to Try Next

1. **Run the application** - See all entities created
2. **Access H2 Console** - View table structures
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Username: `sa`, Password: (blank)

3. **Experiment:**
   - Add new fields to entities
   - Try different generation strategies
   - Create your own embedded types
   - Play with enum mappings

4. **Observe SQL:**
   - Watch CREATE TABLE statements
   - See how sequences work
   - Notice column names from @Column

---

## 📊 Database Tables Created

When you run the app, Hibernate creates these tables:

```sql
-- Product (IDENTITY)
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_code VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(200) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    category VARCHAR(20) NOT NULL,
    status_code TINYINT,
    ...
);

-- Employee (SEQUENCE)
CREATE SEQUENCE employee_sequence START WITH 1000 INCREMENT BY 50;
CREATE TABLE employees (
    id BIGINT PRIMARY KEY,
    employee_code VARCHAR(20) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    street_address VARCHAR(200),  -- From embedded Address
    city VARCHAR(100),
    ...
);

-- Customer (TABLE)
CREATE TABLE id_generator_table (
    sequence_name VARCHAR(255) PRIMARY KEY,
    next_value BIGINT
);
CREATE TABLE customers (
    id BIGINT PRIMARY KEY,
    billing_street VARCHAR(200),   -- Embedded with prefix
    shipping_street VARCHAR(200),  -- Embedded with prefix
    ...
);

-- Document (LOBs)
CREATE TABLE documents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content TEXT,              -- CLOB
    file_data LONGBLOB,        -- BLOB
    large_binary_data LONGBLOB, -- LAZY BLOB
    ...
);
```

---

## 💡 Common Pitfalls Avoided

### ❌ DON'T:
```java
// Don't use ORDINAL
@Enumerated(EnumType.ORDINAL)
private Status status;  // Breaks when enum order changes!

// Don't store large files as BLOBs
@Lob
private byte[] hugeVideo;  // Use file path instead!

// Don't forget @AttributeOverrides
@Embedded
private Address billing;
@Embedded
private Address shipping;  // Columns clash!
```

### ✅ DO:
```java
// Use STRING enums
@Enumerated(EnumType.STRING)
private Status status;

// Store file paths
private String videoPath;  // Reference to file system/S3

// Use @AttributeOverrides
@Embedded
@AttributeOverrides({...})
private Address billing;
```

---

## 🚀 What's Next?

You've mastered Entity Mapping! You now know how to:
- Map Java classes to database tables
- Generate primary keys 5 different ways
- Configure columns with precision
- Handle enums safely
- Embed reusable components
- Store large objects
- Optimize with lazy loading

**Ready for Topic 4: Relationships (Associations)?**

Next, you'll learn:
- One-to-One relationships
- One-to-Many / Many-to-One
- Many-to-Many with join tables
- Bidirectional vs Unidirectional
- Cascading operations
- EAGER vs LAZY fetching
- Orphan removal

**Let me know when you're ready to continue! 🎉**
