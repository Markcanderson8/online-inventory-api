package com.productInventory.exceptions;

import java.rmi.server.ExportException;

public class DataIntegrityViolation extends RuntimeException {

  public DataIntegrityViolation(Exception e) {

  }

  public DataIntegrityViolation(String message) {
    super(message);
  }
}
