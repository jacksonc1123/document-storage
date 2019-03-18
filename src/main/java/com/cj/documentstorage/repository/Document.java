package com.cj.documentstorage.repository;

import org.springframework.http.MediaType;

// import org.springframework.web.multipart.MultipartFile;

public class Document {
  private MediaType contentType;
  private byte[] content;

  public Document() {
    this.contentType = null;
    this.content = null;
  }

  public Document(MediaType contentType, byte[] content) {
    this.contentType = contentType;
    this.content = content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  public void setContentType(MediaType contentType) {
    this.contentType = contentType;
  }

  public byte[] getContent() {
    return this.content;
  }

  public MediaType getContentType() {
    return this.contentType;
  }
}