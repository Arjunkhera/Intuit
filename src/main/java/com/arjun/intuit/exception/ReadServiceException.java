package com.arjun.intuit.exception;

public class ReadServiceException extends Exception {

  public ReadServiceException(String message) {
    super(message);
  }

  public ReadServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
