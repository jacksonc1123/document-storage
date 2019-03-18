package com.cj.documentstorage.service;

import com.cj.documentstorage.repository.Document;

// import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
  public String createDocument(String document, String contentType);
  public Document fetchDocument(String documentID);
  public boolean updateDocument(String documentID, String document);
  public boolean deleteDocument(String documentID);  
}