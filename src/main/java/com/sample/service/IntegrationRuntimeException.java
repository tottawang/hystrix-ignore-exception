package com.sample.service;

public class IntegrationRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public IntegrationRuntimeException() {}

  public IntegrationRuntimeException(String message) {
    super(message);
  }

  public IntegrationRuntimeException(Throwable cause) {
    super(cause);
  }

  public IntegrationRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }
}
