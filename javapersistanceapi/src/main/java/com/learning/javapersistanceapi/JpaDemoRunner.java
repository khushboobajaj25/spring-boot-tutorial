package com.learning.javapersistanceapi;

import com.learning.javapersistanceapi.entity.User;
import com.learning.javapersistanceapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Demo runner to test our JPA setup
 * This runs automatically when the application starts
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JpaDemoRunner implements CommandLineRunner {
    
    private final UserRepository userRepository;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("===== Starting JPA Demo =====");
        
        // Create some sample users
        User user1 = User.builder()
                .name("John Doe")
                .email("john@example.com")
                .age(30)
                .isActive(true)
                .build();
        
        User user2 = User.builder()
                .name("Jane Smith")
                .email("jane@example.com")
                .age(25)
                .isActive(true)
                .build();
        
        User user3 = User.builder()
                .name("Bob Johnson")
                .email("bob@example.com")
                .age(35)
                .isActive(false)
                .build();
        
        // Save users
        log.info("Saving users...");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        
        // Find all users
        log.info("\n--- All Users ---");
        userRepository.findAll().forEach(user -> 
            log.info("User: {} - {} - Age: {}", user.getId(), user.getName(), user.getAge())
        );
        
        // Find by email
        log.info("\n--- Find by Email ---");
        userRepository.findByEmail("john@example.com")
                .ifPresent(user -> log.info("Found: {}", user.getName()));
        
        // Find active users
        log.info("\n--- Active Users ---");
        userRepository.findByIsActive(true)
                .forEach(user -> log.info("Active User: {}", user.getName()));
        
        // Find users older than 28
        log.info("\n--- Users Older Than 28 ---");
        userRepository.findByAgeGreaterThan(28)
                .forEach(user -> log.info("User: {} - Age: {}", user.getName(), user.getAge()));
        
        log.info("\n===== JPA Demo Complete =====");
        log.info("Visit H2 Console at: http://localhost:8080/h2-console");
        log.info("JDBC URL: jdbc:h2:mem:testdb");
        log.info("Username: sa");
        log.info("Password: (leave blank)");
    }
}
