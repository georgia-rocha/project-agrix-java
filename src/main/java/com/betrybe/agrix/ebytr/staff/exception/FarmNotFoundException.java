package com.betrybe.agrix.ebytr.staff.exception;
 
/**
  * EXCEPTION FARM.
  */
public class FarmNotFoundException extends RuntimeException {
  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }

  public FarmNotFoundException(String message) {
    super(message);
  }
}
