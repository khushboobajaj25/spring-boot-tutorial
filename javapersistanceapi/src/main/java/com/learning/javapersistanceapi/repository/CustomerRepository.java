package com.learning.javapersistanceapi.repository;

import com.learning.javapersistanceapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    Optional<Customer> findByCustomerCode(String customerCode);
    
    Optional<Customer> findByEmail(String email);
    
    List<Customer> findByPremiumTrue();
    
    List<Customer> findByBillingAddress_City(String city);

    Optional<Customer> findByPhone(String phone);
    // SELECT * FROM customers WHERE phone = ?

    Optional<Customer> findByPhoneAndCity(String phone, String city);
    // SELECT * FROM customers WHERE phone = ? AND city = ?

    List<Customer> findByLastNameContainingIgnoreCase(String lastName);
    // SELECT * FROM customers WHERE LOWER(last_name) LIKE LOWER('%' || ? || '%')'

    List<Customer> findByActiveTrueOrderByFirstNameAsc();
    // SELECT * FROM customers WHERE active = true ORDER BY first_name ASC

    List<Customer> findByFirstNameStartingWithIgnoreCase(String prefix);
    // SELECT * FROM customers WHERE LOWER(first_name) LIKE LOWER(? || '%')

    List<Customer> findByOrders_TotalAmountGreaterThan(Double amount);
    // SELECT c.* FROM customers c JOIN orders o ON c.id = o.customer_id WHERE o.total_amount > ?

    @Query(value = "SELECT c FROM Customer c WHERE c.email LIKE %:domain", nativeQuery = false)
    List<Customer> findByEmailDomain(String domain);

    List<Customer> findTop5ByOrderByCreatedAtDesc();
    // SELECT * FROM customers ORDER BY created_at DESC LIMIT 5

    List<Customer> findByCreatedAtBetween(java.time.LocalDateTime start, java.time.LocalDateTime end);
    // SELECT * FROM customers WHERE created_at BETWEEN ? AND ?
}

/**
 * findById,
 * findAll,
 * save
 * saveAll
 * delete
 * deletAll
 * deeleteById
 */