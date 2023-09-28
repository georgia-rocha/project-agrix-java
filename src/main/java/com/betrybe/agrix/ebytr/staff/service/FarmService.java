package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.exception.FarmNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.CropRepository;
import com.betrybe.agrix.ebytr.staff.repository.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Farm Service.
 */
@Service
public class FarmService {
  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * POST FARM.
   *
   */
  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * GET ALL FARM's.
   *
   */
  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * GET BY ID FARM.
   *
   */
  public Optional<Farm> getFarmById(Long id) {
    return farmRepository.findById(id);
  }

  /**
   * POST FARM ID CROP.
   *
   */
  public Crop createCrop(Long id, CropDto crop) {
    Optional<Farm> farm = farmRepository.findById(id);

    if (farm.isEmpty()) {
      throw new FarmNotFoundException();
    }
    Crop crop2 = new Crop();
    crop2.setName(crop.name());
    crop2.setPlantedArea(crop.plantedArea());
    crop2.setFarm(farm.get());
    crop2.setHarvestDate(crop.harvestDate());
    crop2.setPlantedDate(crop.plantedDate());

    return cropRepository.save(crop2);
  }
}