# Project Setup - Complete Explanation

## 📋 What We've Set Up

### 1. **Dependencies (pom.xml)**
```xml
- spring-boot-starter-data-jpa: Core JPA functionality
- H2 Database: In-memory database for learning
- PostgreSQL Driver: Production database
- Lombok: Reduces boilerplate code
```

### 2. **Database Configurations**

#### H2 Database (Development)
- **WHAT**: In-memory database, perfect for learning
- **WHY**: Fast, no installation needed, resets on restart
- **WHEN**: Use during development and learning
- **Access**: http://localhost:8080/h2-console

#### PostgreSQL (Production)
- **WHAT**: Production-grade relational database
- **WHY**: Robust, scalable, industry standard
- **WHEN**: Use for real applications
- **Setup**: Requires PostgreSQL installation

---

## 🔧 Configuration Files Explained

### application.properties (Main Configuration)
```properties
spring.jpa.hibernate.ddl-auto=create-drop
```

#### DDL-AUTO Modes Explained:

| Mode | WHAT It Does | WHEN to Use |
|------|--------------|-------------|
| `create` | Drops existing tables and creates new ones on startup | Learning, fresh start each time |
| `create-drop` | Creates tables on startup, drops on shutdown | Testing, demo applications |
| `update` | Updates schema without dropping data | Development (RISKY!) |
| `validate` | Only validates schema, no changes | Production |
| `none` | No schema management | Production with Flyway/Liquibase |

**WHY each mode:**
- **create-drop**: We use this for learning because you get a fresh database each time
- **update**: Dangerous! Can cause data loss or schema issues
- **validate**: Safest for production - ensures schema matches entities
- **none**: When using migration tools for better control

---

### Important JPA Properties Explained

```properties
# Show SQL queries in console
spring.jpa.show-sql=true
```
**WHY**: See exactly what SQL Hibernate generates. Essential for learning!

```properties
# Format SQL for readability
spring.jpa.properties.hibernate.format_sql=true
```
**WHY**: Makes SQL queries easier to read and understand

```properties
# Show SQL parameter bindings
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```
**WHY**: See actual values being passed to SQL queries (e.g., WHERE id = ?)

```properties
# Highlight SQL syntax
spring.jpa.properties.hibernate.highlight_sql=true
```
**WHY**: Colors make SQL easier to read in console

---

## 📁 Database Initialization Files

### schema.sql
- **WHAT**: Runs BEFORE Hibernate creates tables
- **WHEN**: Need custom DDL (sequences, functions, etc.)
- **WHY**: Sometimes you need database-specific features

### data.sql
- **WHAT**: Runs AFTER tables are created
- **WHEN**: Need initial/test data
- **WHY**: Populate database with sample data automatically

**Note**: With `spring.jpa.hibernate.ddl-auto=create-drop`, Hibernate manages schema, so schema.sql is optional.

---

## 🎯 Running the Application

### Step 1: Start the Application
```bash
./mvnw spring-boot:run
```

### Step 2: Check Console Output
You should see:
- SQL CREATE TABLE statements
- INSERT statements for sample users
- Demo output showing JPA operations

### Step 3: Access H2 Console
1. Open browser: http://localhost:8080/h2-console
2. Use these credentials:
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Username: `sa`
   - Password: (leave blank)

### Step 4: Explore the Database
Run SQL queries in H2 console:
```sql
SELECT * FROM users;
SELECT * FROM users WHERE age > 28;
```

---

## 🔍 Understanding the Code

### User Entity
```java
@Entity  // Tells JPA this is a database table
@Table(name = "users")  // Table name (optional, defaults to class name)
```

**WHAT happens**: Hibernate creates this SQL:
```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    age INTEGER,
    is_active BOOLEAN
);
```

### UserRepository
```java
public interface UserRepository extends JpaRepository<User, Long>
```

**WHAT you get automatically**:
- `save()` - INSERT or UPDATE
- `findById()` - SELECT by primary key
- `findAll()` - SELECT all records
- `delete()` - DELETE record
- `count()` - COUNT records

**Plus custom methods**:
- `findByEmail()` - Spring generates: `SELECT * FROM users WHERE email = ?`
- `findByAgeGreaterThan()` - Generates: `SELECT * FROM users WHERE age > ?`

---

## 🚀 Next Steps After Running

1. **Watch the Console**: See SQL queries being generated
2. **Explore H2 Console**: View tables and data
3. **Modify JpaDemoRunner**: Experiment with different queries
4. **Try Different ddl-auto Modes**: See how they behave

---

## 🔄 Switching to PostgreSQL

### Step 1: Install PostgreSQL
```bash
# macOS
brew install postgresql
brew services start postgresql

# Create database
createdb jpa_learning_db
```

### Step 2: Update application.properties
Comment out H2 section, uncomment PostgreSQL section:
```properties
#spring.datasource.url=jdbc:h2:mem:testdb
# ... comment H2 section

spring.datasource.url=jdbc:postgresql://localhost:5432/jpa_learning_db
spring.datasource.username=postgres
spring.datasource.password=your_password
```

### Step 3: Restart Application
PostgreSQL will be used instead of H2!

---

## 🎓 Key Takeaways

1. **H2 is perfect for learning** - no setup, fast, disposable
2. **PostgreSQL for real apps** - production-grade, persistent
3. **ddl-auto modes control schema** - choose wisely!
4. **JPA generates SQL for you** - watch console to learn
5. **Spring Data JPA = productivity** - minimal code, maximum features

---

## ❓ Common Issues

### Issue: Application won't start
**Solution**: Check if port 8080 is already in use
```bash
lsof -i :8080
kill -9 <PID>
```

### Issue: Can't access H2 console
**Solution**: Ensure `spring.h2.console.enabled=true`

### Issue: Tables not created
**Solution**: Check `spring.jpa.hibernate.ddl-auto` setting

### Issue: SQL not showing in console
**Solution**: Ensure `spring.jpa.show-sql=true`

---

## 🎯 What You've Learned

✅ How to configure Spring Boot with JPA  
✅ Difference between H2 and PostgreSQL  
✅ What ddl-auto modes do and when to use them  
✅ How to enable SQL logging for learning  
✅ How to initialize database with schema.sql and data.sql  
✅ How to create basic entities and repositories  
✅ How to test JPA setup with sample code  

---

**Ready to explore deeper? Run the application and watch the magic! 🎉**
