package com.cj.documentstorage.controller;

import com.cj.documentstorage.repository.Document;
import com.cj.documentstorage.service.StorageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage/documents")
public class StorageController {

  @Autowired
  private StorageService storageService;

  private Logger log = LoggerFactory.getLogger(getClass());

  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
  public ResponseEntity<?> createDocument(@RequestHeader("Content-Type") MediaType contentType,
      @RequestBody byte[] content) {
    log.info("POST request Content-Type: " + contentType.toString());
    String documentID = storageService.createDocument(content, contentType);
    return ResponseEntity.status(HttpStatus.CREATED).body(documentID);
  }

  @RequestMapping(value = "/{documentID}", method = RequestMethod.GET)
  public ResponseEntity<?> getDocument(@PathVariable String documentID) {
    Document document = storageService.fetchDocument(documentID);
    if (document == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    log.info("GET request Content-Type: " + document.getContentType().toString());
    return ResponseEntity.status(HttpStatus.OK).contentType(document.getContentType()).body(document.getContent());
  }

  @RequestMapping(value = "/{documentID}", method = RequestMethod.PUT, consumes = MediaType.ALL_VALUE)
  public ResponseEntity<?> updateDocument(@PathVariable String documentID,
      @RequestHeader("Content-Type") MediaType contentType, @RequestBody byte[] content) {
    boolean updateSuccessful = storageService.updateDocument(documentID, content, contentType);
    if (!updateSuccessful) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @RequestMapping(value = "/{documentID}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteDocument(@PathVariable String documentID) {
    boolean deleteSuccessful = storageService.deleteDocument(documentID);
    if (!deleteSuccessful) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}