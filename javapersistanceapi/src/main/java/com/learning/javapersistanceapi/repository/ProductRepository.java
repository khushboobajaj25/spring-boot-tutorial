package com.learning.javapersistanceapi.repository;

import com.learning.javapersistanceapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    Optional<Product> findByProductCode(String productCode);
    
    List<Product> findByCategory(Enum<?> category);
    
    List<Product> findByPriceGreaterThan(BigDecimal price);
    
    List<Product> findByFeaturedTrue();
}
