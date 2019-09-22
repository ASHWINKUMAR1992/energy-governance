package com.ashwin.energygovernance.service;

import static org.mockito.Mockito.mock;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.ashwin.energygovernance.entity.WaterConsumption;
import com.ashwin.energygovernance.entity.Hotel;
import com.ashwin.energygovernance.repository.WaterConsumptionRepository;

public class WaterConsumptionServiceTest {

  private WaterConsumptionRepository repository;
  private WaterConsumptionService service;

  @BeforeEach
  public void init() {
    repository = mock(WaterConsumptionRepository.class);
    service = new WaterConsumptionService(repository);
  }

  @Test
  public void testFindResourceByHotelId() {
    Mockito.when(repository.findByHotelId(BigInteger.valueOf(1))).thenReturn(getWaterConsumptionSet());
    Assertions.assertNotNull(service.findResourceByHotelId(BigInteger.valueOf(1)));
  }

  private Set<WaterConsumption> getWaterConsumptionSet() {
    Set<WaterConsumption> waterConsumptionSet = new HashSet<WaterConsumption>();
    WaterConsumption e1 = new WaterConsumption(BigInteger.valueOf(1),BigInteger.valueOf(40));
    WaterConsumption e2 = new WaterConsumption(BigInteger.valueOf(2),BigInteger.valueOf(60));
    Hotel hotel = new Hotel();
    hotel.setId(BigInteger.valueOf(1));
    e2.setHotel(hotel);
    waterConsumptionSet.add(e1);
    waterConsumptionSet.add(e2);
    return waterConsumptionSet;
  }
}
