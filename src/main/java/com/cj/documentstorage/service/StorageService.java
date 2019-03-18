package com.cj.documentstorage.service;

import com.cj.documentstorage.repository.Document;

import org.springframework.http.MediaType;

// import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
  public String createDocument(byte[] content, MediaType contentType);
  public Document fetchDocument(String documentID);
  public boolean updateDocument(String documentID, byte[] content, MediaType contentType);
  public boolean deleteDocument(String documentID);  
}