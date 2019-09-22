package com.ashwin.energygovernance.mapper;

import java.math.BigInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.ashwin.energygovernance.entity.Hotel;

public class EnergyGovernanceMapperTest {

  private EnergyGovernanceMapper energyGovernanceMapper;

  @BeforeEach
  public void beforeTest() {
    energyGovernanceMapper = new EnergyGovernanceMapper();
  }

  @Test
  public void testToHotelResponse() {
    Assertions.assertNotNull(energyGovernanceMapper.toHotelResponse(getHotelEntity()));
  }

  private Hotel getHotelEntity() {
    Hotel hotel = new Hotel(BigInteger.valueOf(1),"Hotel1","HOT!");
    return hotel;
  }
}
