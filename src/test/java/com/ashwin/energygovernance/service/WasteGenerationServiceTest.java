package com.ashwin.energygovernance.service;

import static org.mockito.Mockito.mock;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.ashwin.energygovernance.entity.Hotel;
import com.ashwin.energygovernance.entity.WasteGeneration;
import com.ashwin.energygovernance.repository.WasteGenerationRepository;

public class WasteGenerationServiceTest {

  private WasteGenerationRepository repository;
  private WasteGenerationService service;

  @BeforeEach
  public void init() {
    repository = mock(WasteGenerationRepository.class);
    service = new WasteGenerationService(repository);
  }

  @Test
  public void testFindResourceByHotelId() {
    Mockito.when(repository.findByHotelId(BigInteger.valueOf(1))).thenReturn(getWasteGenerationSet());
    Assertions.assertNotNull(service.findResourceByHotelId(BigInteger.valueOf(1)));
  }

  private Set<WasteGeneration> getWasteGenerationSet() {
    Set<WasteGeneration> wasteGenerationSet = new HashSet<WasteGeneration>();
    WasteGeneration e1 = new WasteGeneration(BigInteger.valueOf(1),BigInteger.valueOf(40));
    WasteGeneration e2 = new WasteGeneration(BigInteger.valueOf(2),BigInteger.valueOf(60));
    Hotel hotel = new Hotel();
    hotel.setId(BigInteger.valueOf(1));
    e2.setHotel(hotel);
    wasteGenerationSet.add(e1);
    wasteGenerationSet.add(e2);
    return wasteGenerationSet;
  }
}
