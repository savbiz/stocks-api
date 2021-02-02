package com.savbiz.payconiq.stock.web.error;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  public RestResponseEntityExceptionHandler() {
    super();
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String, Object>> handleIncorrectInput(
      final IllegalArgumentException ex, final HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(getGeneralJson(ex, HttpStatus.BAD_REQUEST, request));
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<Map<String, Object>> handleIdNotFound(final HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(getNotFoundJson(request));
  }

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<Map<String, Object>> handleNPE(final NullPointerException ex,
                                                       final HttpServletRequest request) {
    return Optional.ofNullable(ex.getMessage()).isPresent()
        ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getGeneralJson(ex, HttpStatus.BAD_REQUEST, request))
        : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getGeneralJson(ex, HttpStatus.INTERNAL_SERVER_ERROR, request));
  }

  private Map<String, Object> getNotFoundJson(final HttpServletRequest request) {
    final Map<String, Object> errorInfo = new LinkedHashMap<>();

    errorInfo.put("timestamp", LocalDateTime.now());
    errorInfo.put("message", "Stock ID not found!");
    errorInfo.put("path", request.getServletPath());

    return errorInfo;
  }

  private Map<String, Object> getGeneralJson(final Throwable error, final HttpStatus status,
                                             final HttpServletRequest request) {
    final Map<String, Object> errorInfo = new LinkedHashMap<>();

    errorInfo.put("timestamp", LocalDateTime.now());
    errorInfo.put("status", status.value());
    errorInfo.put("error", error.getClass().getSimpleName());
    errorInfo.put("message", error.getMessage());
    errorInfo.put("path", request.getServletPath());

    return errorInfo;
  }

}
