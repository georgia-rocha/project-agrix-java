package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Fertilizer Service.
 */
@Service
public class FertilizerService {
  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * POST FERTILIZER.
   *
   */
  public Fertilizer createFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /**
   * GET ALL FERTILIZERS.
   *
   */
  public List<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  /**
   * GET FERTILIZER BY ID.
   *
   */
  public Optional<Fertilizer> getFertilizerById(Long id) {
    Optional<Fertilizer> fertilizer = fertilizerRepository.findById(id);
    if (fertilizer.isEmpty()) {
      throw new FertilizerNotFoundException();
    }
    return fertilizer;
  }
}
