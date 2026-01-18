package com.learning.javapersistanceapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Document Entity - Demonstrates Large Objects (LOBs)
 * 
 * WHAT: Shows @Lob for storing large data (BLOBs/CLOBs)
 * WHY: Store files, large text, binary data
 * WHEN: Images, PDFs, long descriptions, JSON data
 */
@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 200)
    private String fileName;
    
    @Column(length = 100)
    private String fileType;
    
    /**
     * LOB for large text content (CLOB - Character Large Object)
     * WHAT: Stores large text (> 4000 characters)
     * WHY: Regular VARCHAR/TEXT has limits
     * WHEN: Storing articles, JSON, XML, large descriptions
     * DATABASE: 
     * - PostgreSQL: TEXT
     * - MySQL: LONGTEXT
     * - Oracle: CLOB
     */
    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    
    /**
     * LOB for binary data (BLOB - Binary Large Object)
     * WHAT: Stores binary data (images, PDFs, etc.)
     * WHY: Store files in database
     * WHEN: Small files, documents, images
     * NOTE: For large files, consider storing file path instead!
     * DATABASE:
     * - PostgreSQL: BYTEA
     * - MySQL: LONGBLOB
     * - Oracle: BLOB
     */
    @Lob
    @Column(name = "file_data")
    private byte[] fileData;
    
    /**
     * LAZY fetching for LOB
     * WHAT: Don't load this field unless explicitly accessed
     * WHY: Large data slows down queries
     * WHEN: Large binary/text data that's rarely needed
     * 
     * IMPORTANT: 
     * - Only works with @Lob or relationships
     * - Requires open persistence context to access
     * - Can cause LazyInitializationException if accessed outside transaction
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "large_binary_data")
    private byte[] largeBinaryData;
    
    @Column(name = "file_size")
    private Long fileSize; // in bytes
    
    @Column(name = "uploaded_at")
    @Builder.Default
    private LocalDateTime uploadedAt = LocalDateTime.now();
    
    /**
     * Transient field for base64 representation
     * WHAT: Computed at runtime, not stored
     */
    @Transient
    private String base64Content;
}
