package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller Farm.
 */
@RestController
@RequestMapping(value = "/fertilizers")
public class ControllerFertilizer {
  private final FertilizerService fertilizerService;

  @Autowired
  public ControllerFertilizer(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * POST.
   */
  @PostMapping()
  public ResponseEntity<Fertilizer> createFertilizer(@RequestBody FertilizerDto fertilizeDto) {
    Fertilizer fertilizer = fertilizeDto.toEntity();
    Fertilizer newFertilizer = fertilizerService.createFertilizer(fertilizer);
    return ResponseEntity.status(HttpStatus.CREATED).body(newFertilizer);
  }

  /**
   * GET ALL FERTILIZERS.
   */
  @GetMapping()
  public List<FertilizerDto> getAllFertilizers() {
    return fertilizerService.getAllFertilizers().stream()
        .map(fertilizer -> new FertilizerDto(
            fertilizer.getId(),
            fertilizer.getName(),
            fertilizer.getBrand(),
            fertilizer.getComposition()))
        .collect(Collectors.toList());
  }

  /**
   * GET CROP BY ID.
   *
   */
  @GetMapping("/{id}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Long id) {
    Optional<Fertilizer> fertilizer = fertilizerService.getFertilizerById(id);
    FertilizerDto fertilizerDtos = new FertilizerDto(
            fertilizer.get().getId(),
            fertilizer.get().getName(),
            fertilizer.get().getBrand(),
            fertilizer.get().getComposition()
    );
    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizerDtos);
  }
}
