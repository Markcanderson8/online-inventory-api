package com.productInventory.exceptions;

import org.springframework.stereotype.Service;

public class ServiceUnavailable extends RuntimeException {

  public ServiceUnavailable() {}

  public ServiceUnavailable(String message) {
    super(message);
  }

  public ServiceUnavailable(Exception e) {
    super(e.getCause());
  }
}
