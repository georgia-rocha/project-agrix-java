package com.betrybe.agrix.ebytr.staff.dto;

import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;

/**
 * Dto Fertilizer.
 */
public record FertilizerDto(
      Long id,
      String name,
      String brand,
      String composition
) {
  public Fertilizer toEntity() {
    return new Fertilizer(id, name, brand, composition);
  }
}