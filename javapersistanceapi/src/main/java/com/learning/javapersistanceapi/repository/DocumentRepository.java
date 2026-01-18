package com.learning.javapersistanceapi.repository;

import com.learning.javapersistanceapi.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    
    List<Document> findByFileType(String fileType);
    
    List<Document> findByFileName(String fileName);
}
