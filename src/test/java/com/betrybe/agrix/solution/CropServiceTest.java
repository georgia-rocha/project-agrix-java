package com.betrybe.agrix.solution;

  import static org.junit.jupiter.api.Assertions.*;
  import static org.mockito.Mockito.*;
  import com.betrybe.agrix.ebytr.staff.entity.Crop;
  import com.betrybe.agrix.ebytr.staff.exception.CropNotFoundException;
  import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
  import com.betrybe.agrix.ebytr.staff.repository.CropRepository;
  import com.betrybe.agrix.ebytr.staff.repository.FertilizerRepository;
  import com.betrybe.agrix.ebytr.staff.service.CropService;
  import org.junit.jupiter.api.BeforeEach;
  import org.junit.jupiter.api.Test;
  import org.mockito.Mock;
  import org.mockito.MockitoAnnotations;
  import java.time.LocalDate;
  import java.util.List;
  import java.util.Optional;

public class CropServiceTest {

  @Mock
  private CropRepository cropRepository;

  @Mock
  private FertilizerRepository fertilizerRepository;

  private CropService cropService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    cropService = new CropService(cropRepository, fertilizerRepository);
  }

  @Test
  public void testGetAllCrop() {
    List<Crop> cropList = List.of(new Crop(), new Crop());
    when(cropRepository.findAll()).thenReturn(cropList);

    List<Crop> result = cropService.getAllCrop();

    assertEquals(cropList, result);
  }

  @Test
  public void testGetCropById_ExistingCrop_ReturnsCrop() {
    Long id = 1L;
    Crop expectedCrop = new Crop();
    expectedCrop.setId(id);
    when(cropRepository.findById(id)).thenReturn(Optional.of(expectedCrop));

    Optional<Crop> actualCrop = cropService.getCropById(id);

    assertTrue(actualCrop.isPresent());
    assertEquals(expectedCrop, actualCrop.get());
  }

  @Test
  public void testGetCropById_CropNotFound_ThrowsException() {
    Long id = 2L;
    when(cropRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(CropNotFoundException.class, () -> cropService.getCropById(id));
  }

  @Test
  public void testGetCropsByHarvestDate_HarvestDateInRange_ReturnsCrops() {
    LocalDate dateStart = LocalDate.parse("2023-01-01");
    LocalDate dateEnd = LocalDate.parse("2023-12-31");
    List<Crop> cropList = List.of(new Crop(), new Crop());
    when(cropRepository.getCropsByHarvestDateBetween(dateStart, dateEnd)).thenReturn(cropList);

    List<Crop> result = cropService.getCropsByHarvestDate(dateStart, dateEnd);

    assertEquals(cropList, result);
  }

  @Test
  public void testGetCropsByHarvestDate_NoCropsFound_ThrowsException() {
    LocalDate dateStart = LocalDate.parse("2023-01-01");
    LocalDate dateEnd = LocalDate.parse("2023-12-31");
    when(cropRepository.getCropsByHarvestDateBetween(dateStart, dateEnd)).thenReturn(List.of());

    assertThrows(CropNotFoundException.class, () -> cropService.getCropsByHarvestDate(dateStart, dateEnd));
  }

  @Test
  public void testCreateCropByFertilizerId_InvalidCropId_ThrowsException() {
    Long cropId = 2L;
    Long fertilizerId = 1L;

    when(cropRepository.findById(cropId)).thenReturn(Optional.empty());

    assertThrows(CropNotFoundException.class, () -> cropService.createCropByFertilizerId(cropId, fertilizerId));
  }

  @Test
  public void testCreateCropByFertilizerId_InvalidFertilizerId_ThrowsException() {
    Long cropId = 1L;
    Long fertilizerId = 2L;
    Crop crop = new Crop();
    crop.setId(cropId);

    when(cropRepository.findById(cropId)).thenReturn(Optional.of(crop));
    when(fertilizerRepository.findById(fertilizerId)).thenReturn(Optional.empty());

    assertThrows(FertilizerNotFoundException.class, () -> cropService.createCropByFertilizerId(cropId, fertilizerId));
  }

  @Test
  public void testGetAllFertilizersByCropId_InvalidCropId_ThrowsException() {
    Long cropId = 2L;

    when(cropRepository.findById(cropId)).thenReturn(Optional.empty());

    assertThrows(CropNotFoundException.class, () -> cropService.getAllFertilizersByCropId(cropId));
  }
}
