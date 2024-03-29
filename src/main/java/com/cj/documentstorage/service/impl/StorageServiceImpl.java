package com.cj.documentstorage.service.impl;


// import java.util.ArrayList;
import java.util.HashMap;

import com.cj.documentstorage.repository.Document;
import com.cj.documentstorage.service.StorageService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.text.RandomStringGenerator;

@Service
public class StorageServiceImpl implements StorageService  {

  HashMap<String, Document> documents = new HashMap<String, Document>();

  final char [][] pairs = {{'a','z'},{'0','9'}};

  public String createDocument(byte[] content, MediaType contentType) {
    RandomStringGenerator rsg = new RandomStringGenerator
    .Builder()
    .withinRange(pairs) 
    .build();
    final String documentID = rsg.generate(20);
    Document document = new Document(contentType, content);
    documents.put(documentID, document);
    return documentID;
  }

  public Document fetchDocument(String documentID) {
    return documents.get(documentID);
  }

  public boolean updateDocument(String documentID, byte[] content, MediaType contentType) {
    Document oldDocument = documents.get(documentID);
    if (oldDocument == null) {
      return false;
    } else {
      Document newDocument = new Document(contentType, content);
      documents.put(documentID, newDocument);
      return true;
    }
  }

  public boolean deleteDocument(String documentID) {
    Document toDelete = documents.get(documentID);

    if (toDelete == null) {
      return false;
    } else {
      documents.remove(documentID);
      return true;
    }
  }
}