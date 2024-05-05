package artur.maschenko.dateconverter.component;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

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
@ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
    HttpClientErrorException.class,
    HttpMessageNotReadableException.class,
    MethodArgumentNotValidException.class,
    MissingServletRequestParameterException.class,
    ConstraintViolationException.class
  })
  public ErrorResponse handleBadRequestException(Exception ex) {
    logger.error("Entity not found exception: {}", ex.getMessage());
    return new ErrorResponse(HttpStatus.BAD_REQUEST, "Error 400: " + ex.getMessage());
  }

  /**
   * Handle entity not found exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
@ExceptionHandler(value = {EntityNotFoundException.class})
  public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
    logger.error("Illegal argument exception: {}", ex.getMessage());
    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.NOT_FOUND, "Error 404: " + ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  /**
   * Handle http request method not supported exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
@ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
  public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException ex) {
    logger.error("Method not allowed: {}", ex.getMessage());
    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, "Error 405: " + ex.getMessage());
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
  }

  /**
   * Handle exception response entity.
   *
   * @param ex the ex
   * @return the response entity
   */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(RuntimeException.class)
  public ErrorResponse handleInternalServerError(RuntimeException ex) {
    logger.error("Internal server error: {}", ex.getMessage());
    return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error 500: " + ex.getMessage());
  }

  /** The type Error response. */
public static class ErrorResponse {
    private final int status;
    private final String message;

    /**
     * Instantiates a new Error response.
     *
     * @param status the status
     * @param message the message
     */
public ErrorResponse(HttpStatus status, String message) {
      this.status = status.value();
      this.message = message;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
public int getStatus() {
      return status;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
public String getMessage() {
      return message;
    }
  }
}
