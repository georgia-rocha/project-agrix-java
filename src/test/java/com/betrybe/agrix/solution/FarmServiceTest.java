package com.betrybe.agrix.solution;

  import com.betrybe.agrix.ebytr.staff.entity.Farm;
  import com.betrybe.agrix.ebytr.staff.repository.CropRepository;
  import com.betrybe.agrix.ebytr.staff.repository.FarmRepository;
  import com.betrybe.agrix.ebytr.staff.service.FarmService;
  import org.junit.jupiter.api.BeforeEach;
  import org.junit.jupiter.api.Test;
  import org.junit.jupiter.api.extension.ExtendWith;
  import org.mockito.Mock;
  import org.mockito.MockitoAnnotations;
  import org.mockito.junit.jupiter.MockitoExtension;
  import java.util.List;
  import java.util.Optional;
  import static org.junit.jupiter.api.Assertions.*;
  import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FarmServiceTest {

  @Mock
  private FarmRepository farmRepository;

  @Mock
  private CropRepository cropRepository;

  private FarmService farmService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    farmService = new FarmService(farmRepository, cropRepository);
  }

  @Test
  public void testCreateFarm() {
    Farm farmToCreate = new Farm();
    when(farmRepository.save(farmToCreate)).thenReturn(farmToCreate);

    Farm createdFarm = farmService.createFarm(farmToCreate);

    assertNotNull(createdFarm);
  }

  @Test
  public void testGetAllFarms() {
    List<Farm> farmList = List.of(new Farm(), new Farm());
    when(farmRepository.findAll()).thenReturn(farmList);

    List<Farm> result = farmService.getAllFarms();

    assertEquals(farmList, result);
  }

  @Test
  public void testGetFarmById_ExistingFarm_ReturnsFarm() {
    Long id = 1L;
    Farm expectedFarm = new Farm();
    expectedFarm.setId(id);
    when(farmRepository.findById(id)).thenReturn(Optional.of(expectedFarm));

    Optional<Farm> actualFarm = farmService.getFarmById(id);

    assertTrue(actualFarm.isPresent());
    assertEquals(expectedFarm, actualFarm.get());
  }
}
