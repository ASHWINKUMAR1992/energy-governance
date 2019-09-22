package com.ashwin.energygovernance.service;

import static org.mockito.Mockito.mock;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.ashwin.energygovernance.entity.ElectricityConsumption;
import com.ashwin.energygovernance.entity.Hotel;
import com.ashwin.energygovernance.repository.ElectricityConsumptionRepository;

public class ElectricityConsumptionServiceTest {
  
  private ElectricityConsumptionRepository repository;
  private ElectricityConsumptionService service;

  @BeforeEach
  public void init() {
    repository = mock(ElectricityConsumptionRepository.class);
    service = new ElectricityConsumptionService(repository);
  }

  @Test
  public void testFindResourceByHotelId() {
    Mockito.when(repository.findByHotelId(BigInteger.valueOf(1))).thenReturn(getElectricityConsumptionSet());
    Assertions.assertNotNull(service.findResourceByHotelId(BigInteger.valueOf(1)));
  }

  private Set<ElectricityConsumption> getElectricityConsumptionSet() {
    Set<ElectricityConsumption> electricityConsumptionSet = new HashSet<ElectricityConsumption>();
    ElectricityConsumption e1 = new ElectricityConsumption(BigInteger.valueOf(1),BigInteger.valueOf(40));
    ElectricityConsumption e2 = new ElectricityConsumption(BigInteger.valueOf(2),BigInteger.valueOf(60));
    Hotel hotel = new Hotel();
    hotel.setId(BigInteger.valueOf(1));
    e1.setHotel(hotel);
    e2.setHotel(hotel);
    electricityConsumptionSet.add(e1);
    electricityConsumptionSet.add(e2);
    return electricityConsumptionSet;
  }
}
