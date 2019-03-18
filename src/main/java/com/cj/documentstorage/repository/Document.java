package com.cj.documentstorage.repository;

// import org.springframework.web.multipart.MultipartFile;

public class Document {
  private String contentType;
  private String document;

  public Document() {
    this.contentType = null;
    this.document = null;
  }

  public Document(String contentType, String document) {
    this.contentType = contentType;
    this.document = document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getDocument() {
    return this.document;
  }

  public String getContentType() {
    return this.contentType;
  }
}