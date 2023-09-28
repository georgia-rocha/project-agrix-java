package com.betrybe.agrix.ebytr.staff.dto;

import com.betrybe.agrix.ebytr.staff.entity.Farm;

/**
 * Dto Farm.
 */
public record FarmDto(Long id, String name, Double size) {
  public Farm toFarm() {
    return new Farm(id, name, size);
  }
}
