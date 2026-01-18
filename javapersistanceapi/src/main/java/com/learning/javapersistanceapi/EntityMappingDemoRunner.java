package com.learning.javapersistanceapi;

import com.learning.javapersistanceapi.entity.*;
import com.learning.javapersistanceapi.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity Mapping Demo - Demonstrates all mapping concepts
 */
@Component
@Order(2)  // Run after JpaDemoRunner
@RequiredArgsConstructor
@Slf4j
public class EntityMappingDemoRunner implements CommandLineRunner {
    
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final DocumentRepository documentRepository;
    private final DepartmentRepository departmentRepository;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("\n\n========================================");
        log.info("ENTITY MAPPING DEMONSTRATION");
        log.info("========================================\n");
        
        demonstrateProductMapping();
        demonstrateEmployeeMapping();
        demonstrateCustomerMapping();
        demonstrateDocumentMapping();
        demonstrateDepartmentMapping();
        
        log.info("\n========================================");
        log.info("ENTITY MAPPING DEMO COMPLETE!");
        log.info("========================================\n");
    }
    
    /**
     * Demonstrates: IDENTITY strategy, @Column, @Enumerated, @Transient
     */
    private void demonstrateProductMapping() {
        log.info("\n--- PRODUCT Entity (IDENTITY Strategy) ---");
        
        Product laptop = Product.builder()
                .productCode("LAPTOP-001")
                .name("Dell XPS 15")
                .description("High-performance laptop for developers")
                .price(new BigDecimal("1299.99"))
                .stockQuantity(50)
                .category(ProductCategory.ELECTRONICS)
                .status(ProductStatus.ACTIVE)
                .manufactureDate(LocalDate.of(2024, 1, 15))
                .featured(true)
                .build();
        
        Product savedLaptop = productRepository.save(laptop);
        log.info("✓ Saved Product with IDENTITY ID: {}", savedLaptop.getId());
        log.info("  - Product Code: {}", savedLaptop.getProductCode());
        log.info("  - Category (STRING): {}", savedLaptop.getCategory());
        log.info("  - Status (ORDINAL): {}", savedLaptop.getStatus());
        log.info("  - Price: ${}", savedLaptop.getPrice());
        log.info("  - Discounted Price (Transient): ${}", savedLaptop.getDiscountedPrice());
        
        // Create more products
        productRepository.save(Product.builder()
                .productCode("BOOK-001")
                .name("Clean Code")
                .description("A Handbook of Agile Software Craftsmanship")
                .price(new BigDecimal("39.99"))
                .stockQuantity(100)
                .category(ProductCategory.BOOKS)
                .status(ProductStatus.ACTIVE)
                .build());
        
        log.info("✓ Total Products: {}", productRepository.count());
    }
    
    /**
     * Demonstrates: SEQUENCE strategy, @Embedded
     */
    private void demonstrateEmployeeMapping() {
        log.info("\n--- EMPLOYEE Entity (SEQUENCE Strategy) ---");
        
        Address address = Address.builder()
                .street("123 Main Street")
                .city("San Francisco")
                .state("CA")
                .postalCode("94102")
                .country("USA")
                .build();
        
        Employee employee = Employee.builder()
                .employeeCode("EMP-2024-001")
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@company.com")
                .address(address)
                .hireDate(LocalDate.now())
                .active(true)
                .build();
        
        Employee savedEmployee = employeeRepository.save(employee);
        log.info("✓ Saved Employee with SEQUENCE ID: {}", savedEmployee.getId());
        log.info("  - Employee Code: {}", savedEmployee.getEmployeeCode());
        log.info("  - Full Name: {} {}", savedEmployee.getFirstName(), savedEmployee.getLastName());
        log.info("  - Embedded Address:");
        log.info("    * Street: {}", savedEmployee.getAddress().getStreet());
        log.info("    * City: {}", savedEmployee.getAddress().getCity());
        log.info("    * State: {}", savedEmployee.getAddress().getState());
        
        // Note: ID starts from 1000 (initialValue in @SequenceGenerator)
        log.info("  - Note: SEQUENCE starts from initialValue=1000");
        log.info("✓ Total Employees: {}", employeeRepository.count());
    }
    
    /**
     * Demonstrates: TABLE strategy, Multiple @Embedded with @AttributeOverrides
     */
    private void demonstrateCustomerMapping() {
        log.info("\n--- CUSTOMER Entity (TABLE Strategy) ---");
        
        Address billingAddr = Address.builder()
                .street("456 Oak Avenue")
                .city("New York")
                .state("NY")
                .postalCode("10001")
                .country("USA")
                .build();
        
        Address shippingAddr = Address.builder()
                .street("789 Pine Road")
                .city("Boston")
                .state("MA")
                .postalCode("02101")
                .country("USA")
                .build();
        
        Customer customer = Customer.builder()
                .customerCode("CUST-2024-001")
                .fullName("Jane Smith")
                .email("jane.smith@email.com")
                .phone("555-1234")
                .billingAddress(billingAddr)
                .shippingAddress(shippingAddr)
                .premium(true)
                .build();
        
        Customer savedCustomer = customerRepository.save(customer);
        log.info("✓ Saved Customer with TABLE ID: {}", savedCustomer.getId());
        log.info("  - Customer Code: {}", savedCustomer.getCustomerCode());
        log.info("  - Billing Address: {}, {}", 
                savedCustomer.getBillingAddress().getCity(),
                savedCustomer.getBillingAddress().getState());
        log.info("  - Shipping Address: {}, {}", 
                savedCustomer.getShippingAddress().getCity(),
                savedCustomer.getShippingAddress().getState());
        log.info("  - Premium: {}", savedCustomer.getPremium());
        log.info("  - Note: TABLE strategy creates 'id_generator_table'");
        log.info("✓ Total Customers: {}", customerRepository.count());
    }
    
    /**
     * Demonstrates: @Lob, @Basic(fetch = LAZY)
     */
    private void demonstrateDocumentMapping() {
        log.info("\n--- DOCUMENT Entity (LOB Demonstration) ---");
        
        String largeContent = "This is a large text content that would be stored as CLOB. ".repeat(100);
        byte[] smallFile = "Sample file content".getBytes();
        byte[] largeFile = new byte[1024 * 100]; // 100KB
        
        Document document = Document.builder()
                .fileName("sample-document.txt")
                .fileType("text/plain")
                .content(largeContent)
                .fileData(smallFile)
                .largeBinaryData(largeFile)
                .fileSize((long) largeFile.length)
                .build();
        
        Document savedDoc = documentRepository.save(document);
        log.info("✓ Saved Document with ID: {}", savedDoc.getId());
        log.info("  - File Name: {}", savedDoc.getFileName());
        log.info("  - Content Length (CLOB): {} characters", savedDoc.getContent().length());
        log.info("  - File Data (BLOB): {} bytes", savedDoc.getFileData().length);
        log.info("  - Large Binary (LAZY BLOB): {} bytes", savedDoc.getFileSize());
        log.info("  - Note: largeBinaryData is LAZY loaded");
        log.info("✓ Total Documents: {}", documentRepository.count());
    }
    
    /**
     * Demonstrates: AUTO strategy
     */
    private void demonstrateDepartmentMapping() {
        log.info("\n--- DEPARTMENT Entity (AUTO Strategy) ---");
        
        Department it = Department.builder()
                .departmentCode("IT")
                .name("Information Technology")
                .description("Manages company's technology infrastructure")
                .active(true)
                .build();
        
        Department hr = Department.builder()
                .departmentCode("HR")
                .name("Human Resources")
                .description("Manages employee relations and recruitment")
                .active(true)
                .build();
        
        Department savedIT = departmentRepository.save(it);
        Department savedHR = departmentRepository.save(hr);
        
        log.info("✓ Saved Department (IT) with AUTO ID: {}", savedIT.getId());
        log.info("✓ Saved Department (HR) with AUTO ID: {}", savedHR.getId());
        log.info("  - Note: AUTO strategy lets Hibernate choose (usually SEQUENCE)");
        log.info("✓ Total Departments: {}", departmentRepository.count());
    }
}
