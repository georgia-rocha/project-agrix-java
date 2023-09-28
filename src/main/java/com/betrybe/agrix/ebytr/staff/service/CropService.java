package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.exception.CropNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.CropRepository;
import com.betrybe.agrix.ebytr.staff.repository.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Crop Service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * GET CROP.
   *
   */
  public List<Crop> getAllCrop() {
    return cropRepository.findAll();
  }

  /**
   * GET CROP BY ID.
   *
   */
  public Optional<Crop> getCropById(Long id) {
    Optional<Crop> crop = cropRepository.findById(id);
    if (crop.isEmpty()) {
      throw new CropNotFoundException();
    }
    return crop;
  }

  /**
   * GET CROPS BY HAVESTDATE .
   *
   */
  public List<Crop> getCropsByHarvestDate(LocalDate dateStart, LocalDate dateEnd) {
    List<Crop> crop = cropRepository.getCropsByHarvestDateBetween(dateStart, dateEnd);

    if (crop.isEmpty()) {
      throw new CropNotFoundException();
    }
    return crop;
  }

  /**
   * POST CROPS BY ID BY FERTILIZERS BY FERTILIZERS ID .
   *
   */
  public void createCropByFertilizerId(Long cropId, Long fertilizerId) {
    Optional<Crop> crop = cropRepository.findById(cropId);
    if (crop.isEmpty()) {
      throw new CropNotFoundException();
    }

    Optional<Fertilizer> fertilizer = fertilizerRepository.findById(fertilizerId);
    if (fertilizer.isEmpty()) {
      throw new FertilizerNotFoundException();
    }

    crop.get().getFertilizer().add(fertilizer.get());
    cropRepository.save(crop.get());
  }

  /**
   * GET CROP BY ID BY ALL FERTILIZERS.
   *
   */
  public List<Fertilizer> getAllFertilizersByCropId(Long cropId) {
    Optional<Crop> crop = cropRepository.findById(cropId);
    if (crop.isEmpty()) {
      throw new CropNotFoundException();
    }
    return crop.get().getFertilizer();
  }
}