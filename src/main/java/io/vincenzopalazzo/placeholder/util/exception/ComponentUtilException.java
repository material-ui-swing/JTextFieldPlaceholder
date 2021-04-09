package io.vincenzopalazzo.placeholder.util.exception;

public class ComponentUtilException extends RuntimeException {

  public ComponentUtilException() {}

  public ComponentUtilException(String message) {
    super(message);
  }

  public ComponentUtilException(String message, Throwable cause) {
    super(message, cause);
  }

  public ComponentUtilException(Throwable cause) {
    super(cause);
  }

  public ComponentUtilException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
