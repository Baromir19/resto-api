package com.resto.pizzeria_api.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.databind.exc.InvalidFormatException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(ApiNotFoundException.class)
  public ResponseEntity<ApiErrorResponse> handleApiNotFoundException(
      final ApiNotFoundException ex,
      final HttpServletRequest request) {
    ApiErrorResponse errorResponse = new ApiErrorResponse(
        ex.getMessage(),
        "CODE_NOT_FOUND"
    );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiErrorResponse> handleValidationException(
      final MethodArgumentNotValidException ex,
      final HttpServletRequest request) {
    String message = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> error.getField() + " : " + error.getDefaultMessage())
        .collect(Collectors.joining(", "));

    ApiErrorResponse errorResponse = new ApiErrorResponse(
        message,
        "CODE_NOT_VALIDATED"
    );
    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ApiErrorResponse> handleConstraintViolationException(
      final ConstraintViolationException ex,
      final HttpServletRequest request) {
    String message = ex.getConstraintViolations()
        .stream()
        .map(violation -> violation.getPropertyPath() + " : " + violation.getMessage())
        .collect(Collectors.joining(", "));

    ApiErrorResponse errorResponse = new ApiErrorResponse(
        message,
        "CODE_DB_CONSTRAINT_VIOLATION"
    );
    return ResponseEntity.badRequest().body(errorResponse);
  }
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolationException(
      final DataIntegrityViolationException ex,
      final HttpServletRequest request) {
    ApiErrorResponse errorResponse = new ApiErrorResponse(
        "Violation d'intégrité des données : valeur déjà existante ou contrainte SQL non respectée",
        "CODE_DB_INTEGRITY_VIOLATION"
    );
    return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiErrorResponse> handleJsonBodyException(
      final HttpMessageNotReadableException ex,
      final HttpServletRequest request) {
    ApiErrorResponse errorResponse = null;

    if (ex.getCause() instanceof InvalidFormatException) {
      errorResponse = new ApiErrorResponse(
          "Le format de requête n'est pas valide",
          "CODE_INVALID_FORMAT"
      );
    } else { // UnexpectedEndOfInputException || StreamReadException || MismatchedInputException
      log.error("Erreur: requête Http n'est pas valide", ex);

      errorResponse = new ApiErrorResponse(
          "Le format de requête n'est pas valide",
          "CODE_HTTP_NOT_READABLE"
      );
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiErrorResponse> handleGenericException(
      final Exception ex,
      final HttpServletRequest request) {
    log.error("Erreur indéfini", ex);

    ApiErrorResponse errorResponse = new ApiErrorResponse(
        "Une erreur interne est survenue",
        "CODE_UNKNOWN"
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }
}
