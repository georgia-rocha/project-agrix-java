package com.betrybe.agrix.solution;

  import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
  import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
  import com.betrybe.agrix.ebytr.staff.repository.FertilizerRepository;
  import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
  import org.junit.jupiter.api.BeforeEach;
  import org.junit.jupiter.api.Test;
  import org.mockito.Mock;
  import org.mockito.MockitoAnnotations;
  import java.util.List;
  import java.util.Optional;
  import static org.junit.jupiter.api.Assertions.*;
  import static org.mockito.Mockito.when;

public class FertilizersServiceTest {

  @Mock
  private FertilizerRepository fertilizerRepository;

  private FertilizerService fertilizerService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    fertilizerService = new FertilizerService(fertilizerRepository);
  }

  @Test
  public void testCreateFertilizer() {
    Fertilizer fertilizerToCreate = new Fertilizer();
    when(fertilizerRepository.save(fertilizerToCreate)).thenReturn(fertilizerToCreate);

    Fertilizer createdFertilizer = fertilizerService.createFertilizer(fertilizerToCreate);

    assertNotNull(createdFertilizer);
  }

  @Test
  public void testGetAllFertilizers() {
    List<Fertilizer> fertilizerList = List.of(new Fertilizer(), new Fertilizer());
    when(fertilizerRepository.findAll()).thenReturn(fertilizerList);

    List<Fertilizer> result = fertilizerService.getAllFertilizers();

    assertEquals(fertilizerList, result);
  }

  @Test
  public void testGetFertilizerById_ExistingFertilizer_ReturnsFertilizer() {
    Long id = 1L;
    Fertilizer expectedFertilizer = new Fertilizer();
    expectedFertilizer.setId(id);
    when(fertilizerRepository.findById(id)).thenReturn(Optional.of(expectedFertilizer));

    Optional<Fertilizer> actualFertilizer = fertilizerService.getFertilizerById(id);

    assertTrue(actualFertilizer.isPresent());
    assertEquals(expectedFertilizer, actualFertilizer.get());
  }

  @Test
  public void testGetFertilizerById_FertilizerNotFound_ThrowsException() {
    Long id = 2L;
    when(fertilizerRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(FertilizerNotFoundException.class, () -> fertilizerService.getFertilizerById(id));
  }
}
