package com.cj.documentstorage.controller;

import com.cj.documentstorage.service.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage/documents")
public class StorageController {

  @Autowired
	private StorageService storageService;

  @RequestMapping(method=RequestMethod.POST, consumes=MediaType.ALL_VALUE)
  public ResponseEntity<String> createDocument(@RequestBody String document, @RequestHeader("content-type") String contentType) {
    String documentID = storageService.createDocument(document);
    return new ResponseEntity<>(documentID, HttpStatus.CREATED);
  }

  @RequestMapping(value="/{documentID}", method=RequestMethod.GET)
  public ResponseEntity<String> getDocument(@PathVariable String documentID) {
    if (documentID == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    String document = storageService.fetchDocument(documentID);
    if (document == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(document, HttpStatus.OK);
  }

  @RequestMapping(value="/{documentID}", method=RequestMethod.PUT)
  public ResponseEntity<String> updateDocument(@PathVariable String documentID, @RequestBody String document) {
    if (documentID == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    boolean updateSuccessful = storageService.updateDocument(documentID, document);
    if (!updateSuccessful) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value="/{documentID}", method=RequestMethod.DELETE)
  public ResponseEntity<String> deleteDocument(@PathVariable String documentID) {
    if (documentID == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    boolean deleteSuccessful = storageService.deleteDocument(documentID);
    if (!deleteSuccessful) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
}