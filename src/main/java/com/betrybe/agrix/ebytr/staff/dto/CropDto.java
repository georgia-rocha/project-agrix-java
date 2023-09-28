package com.betrybe.agrix.ebytr.staff.dto;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import java.time.LocalDate;

/**
 * Dto Crop.
 */
public record CropDto(
      Long id,
      String name,
      Double plantedArea,
      Long farmId,
      LocalDate plantedDate,
      LocalDate harvestDate
) {
  public Crop toEntity() {
    return new Crop(id, name, plantedArea, null, plantedDate, harvestDate, null);
  }
}
