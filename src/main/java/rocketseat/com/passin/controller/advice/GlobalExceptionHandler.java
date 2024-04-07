package rocketseat.com.passin.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rocketseat.com.passin.execption.AlreadyRegisteredException;
import rocketseat.com.passin.execption.EventIsFullException;
import rocketseat.com.passin.execption.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> handleNotFoundException(NotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(exception.getMessage());
  }

  @ExceptionHandler(AlreadyRegisteredException.class)
  public ResponseEntity<String> handleAlreadyRegisteredException(AlreadyRegisteredException exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exception.getMessage());
  }

  @ExceptionHandler(EventIsFullException.class)
  public ResponseEntity<String> handleEventIsFullException(EventIsFullException exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exception.getMessage());
  }
}
