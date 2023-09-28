package com.betrybe.agrix.ebytr.staff.exception;

/**
 * EXCEPTION CROP.
 */
public class CropNotFoundException extends RuntimeException {
  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }

  public CropNotFoundException(String message) {
    super(message);
  }
}

