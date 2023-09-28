package com.betrybe.agrix.ebytr.staff.exception;

/**
 * EXCEPTION FERTILIZERS.
 */
public class FertilizerNotFoundException extends RuntimeException {
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }

  public FertilizerNotFoundException(String message) {
    super(message);
  }
}
