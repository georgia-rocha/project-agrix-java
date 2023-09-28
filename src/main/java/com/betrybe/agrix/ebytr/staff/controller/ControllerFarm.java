package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.dto.FarmDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.exception.FarmNotFoundException;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import com.betrybe.agrix.ebytr.staff.service.FarmService;
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
@RequestMapping(value = "/farms")
public class ControllerFarm {
  private final CropService cropService;
  private final FarmService farmService;

  @Autowired
  public ControllerFarm(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * POST.
   */
  @PostMapping()
  public ResponseEntity<Farm> createFarm(@RequestBody FarmDto farmDto) {
    Farm farm = farmDto.toFarm();
    Farm newFarm = farmService.createFarm(farm);
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  /**
   * GET ALL.
   */
  @GetMapping() 
  public List<FarmDto> getAllFarms() {
    return farmService.getAllFarms().stream()
        .map(farm -> new FarmDto(farm.getId(), farm.getName(), farm.getSize()))
        .collect(Collectors.toList());
  }

  /**
   * GET BY ID.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Object> getFarmById(@PathVariable Long id) {
    Optional<Farm> getById = farmService.getFarmById(id);

    if (getById.isPresent()) {
      Farm farm = getById.get();
      FarmDto farmDto = new FarmDto(farm.getId(), farm.getName(), farm.getSize());
      return ResponseEntity.ok(farmDto);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }
  }

  /**
   * POST FARM BY ID CROPS.
   */
  @PostMapping("/{id}/crops")
  public ResponseEntity<CropDto> createCrop(@PathVariable Long id, @RequestBody CropDto cropDto) {
    Crop newFarm = farmService.createCrop(id, cropDto);

    CropDto cropDto2 = new CropDto(newFarm.getId(), newFarm.getName(), newFarm.getPlantedArea(),
        newFarm.getFarm().getId(), newFarm.getPlantedDate(), newFarm.getHarvestDate());
    return ResponseEntity.status(HttpStatus.CREATED).body(cropDto2);
  }

  /**
  * GET FARM ID CROP.
  *
  */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getCropsByFarmId(@PathVariable Long farmId) {
    Optional<Farm> farm = farmService.getFarmById(farmId);

    if (farm.isEmpty()) {
      throw new FarmNotFoundException();
    }
    List<Crop> crops = farm.get().getCrops();
    List<CropDto> cropDtos = crops.stream()
            .map(crop -> new CropDto(
                crop.getId(),
                crop.getName(),
                crop.getPlantedArea(),
                crop.getFarm().getId(), crop.getPlantedDate(), crop.getHarvestDate()))
            .collect(Collectors.toList());

    return ResponseEntity.ok(cropDtos);
  }
}