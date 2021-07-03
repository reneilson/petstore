package com.example.petstore.errors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request
  ) {
	  return ResponseEntity
	          .status(HttpStatus.UNPROCESSABLE_ENTITY)
	          .body(ex.getMessage());
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Object> handleItemNotFoundException(
      NotFoundException itemNotFoundException, 
      WebRequest request
  ){
	  return ResponseEntity
	          .status(HttpStatus.NOT_FOUND)
	          .body(itemNotFoundException.getMessage());
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Object> handleAllUncaughtException(
      RuntimeException exception, 
      WebRequest request
  ){
	  return ResponseEntity
	          .status(HttpStatus.INTERNAL_SERVER_ERROR)
	          .body(exception.getMessage());
  }

  @Override
  public ResponseEntity<Object> handleExceptionInternal(
      Exception ex,
      Object body,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    return this.buildErrorResponse(ex,status,request);
  }
  

  private ResponseEntity<Object> buildErrorResponse(
      Exception exception,
      HttpStatus httpStatus,
      WebRequest request
  ) {
	  ApiError errorResponse = new ApiError(
        httpStatus, 
        exception
    );
    return ResponseEntity.status(httpStatus).body(errorResponse);
  }

}
