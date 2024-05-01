package artur.maschenko.dateconverter.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import jakarta.persistence.EntityNotFoundException;

/** The type Error controller advice. */
@RestControllerAdvice
public class ErrorControllerAdvice {
  private static final Logger logger = LoggerFactory.getLogger(ErrorControllerAdvice.class);

  /**
   * Handle illegal argument exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(value = {IllegalArgumentException.class})
  public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
    logger.error("Entity not found exception: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error 400: " + ex.getMessage());
  }

  /**
   * Handle entity not found exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(value = {EntityNotFoundException.class})
  public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
    logger.error("Illegal argument exception: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error 404: " + ex.getMessage());
  }

  /**
   * Handle http request method not supported exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
  public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException ex) {
    logger.error("Method not allowed: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        .body("Error 405: " + ex.getMessage());
  }

  /**
   * Handle exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<String> handleException(Exception ex) {
    logger.error("Internal server error: {}", ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Error 500: " + ex.getMessage());
  }
}
