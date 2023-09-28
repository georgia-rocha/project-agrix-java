package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crontroller Crop.
 */
@RestController
@RequestMapping(value = "/crops")
public class ControllerCrop {

  private final CropService cropService;

  @Autowired
  public ControllerCrop(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * GET CROP.
   *
   */
  @GetMapping()
  public ResponseEntity<List<CropDto>> getAllCrop() {
    List<Crop> crops = cropService.getAllCrop();
    List<CropDto> cropDtos = new ArrayList<>();

    for (Crop crop : crops) {
      CropDto cropDto = new CropDto(
          crop.getId(),
          crop.getName(),
          crop.getPlantedArea(),
          crop.getFarm().getId(),
              crop.getPlantedDate(), crop.getHarvestDate()
      );
      cropDtos.add(cropDto);
    }
    return ResponseEntity.ok(cropDtos);
  }

  /**
   * GET CROP BY ID.
   *
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long id) {
    Optional<Crop> crop = cropService.getCropById(id);
    CropDto cropDtos = new CropDto(
        crop.get().getId(),
        crop.get().getName(),
        crop.get().getPlantedArea(),
        crop.get().getFarm().getId(),
            crop.get().getPlantedDate(), crop.get().getHarvestDate()
    );
    return ResponseEntity.ok(cropDtos);
  }

  /**
   * GET CROPS BY HARVESTDATE.
   *
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> getCropsByHarvestDate(
        @RequestParam("start") LocalDate dateStart,
        @RequestParam("end") LocalDate dateEnd) {

    List<Crop> crops = cropService.getCropsByHarvestDate(dateStart, dateEnd);

    List<CropDto> cropByHarvest = new ArrayList<>();

    for (Crop crop : crops) {
      CropDto cropDto = new CropDto(
              crop.getId(),
              crop.getName(),
              crop.getPlantedArea(),
              crop.getFarm().getId(),
              crop.getPlantedDate(), crop.getHarvestDate()
      );
      cropByHarvest.add(cropDto);
    }
    return ResponseEntity.ok(cropByHarvest);
  }

  /**
   * POST CROPS BY ID BY FERTILIZERS BY ID.
   *
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> createCropByFertilizer(
          @PathVariable Long cropId,
          @PathVariable Long fertilizerId) {
    cropService.createCropByFertilizerId(cropId, fertilizerId);

    return ResponseEntity.status(HttpStatus.CREATED)
            .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * GET CROP BY ID BY FERTILIZERS.
   *
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getCropAllFertilizers(
          @PathVariable Long cropId
  ) {
    List<Fertilizer> fertilizers = cropService.getAllFertilizersByCropId(cropId);
    List<FertilizerDto> fertilizersAll = new ArrayList<>();

    for (Fertilizer f : fertilizers) {
      FertilizerDto fertilizerDto = new FertilizerDto(
              f.getId(),
              f.getName(),
              f.getBrand(),
              f.getComposition()
      );
      fertilizersAll.add(fertilizerDto);
    }

    return ResponseEntity.ok(fertilizersAll);
  }
}