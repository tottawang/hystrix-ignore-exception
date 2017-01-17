package com.sample.service;

public class HystrixRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public HystrixRuntimeException() {}

  public HystrixRuntimeException(String message) {
    super(message);
  }

  public HystrixRuntimeException(Throwable cause) {
    super(cause);
  }

  public HystrixRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }
}
