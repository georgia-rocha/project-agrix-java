package com.betrybe.agrix.ebytr.staff.controller.advice;

import com.betrybe.agrix.ebytr.staff.exception.CropNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.FarmNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
  * REST CONTROLLER ADVICE.
  *
  */
@RestControllerAdvice
public class Advice {
  @ExceptionHandler({
      FarmNotFoundException.class,
      CropNotFoundException.class,
      PersonNotFoundException.class,
      FertilizerNotFoundException.class
  })
  public ResponseEntity<String> notFound(RuntimeException error) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
  }
}
