package com.ashwin.energygovernance.service;

import java.math.BigInteger;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ashwin.energygovernance.common.repository.XRepository;
import com.ashwin.energygovernance.common.service.AbstractService;
import com.ashwin.energygovernance.entity.WaterConsumption;
import com.ashwin.energygovernance.repository.WaterConsumptionRepository;

@Service
public class WaterConsumptionService extends AbstractService<WaterConsumption, BigInteger> {

  @Autowired
  private WaterConsumptionRepository waterConsumptionRepository;

  public Set<WaterConsumption> findResourceByHotelId(BigInteger hotelId) {
    return waterConsumptionRepository.findByHotelId(hotelId);
  }

  @Override
  protected String getEntityName() {
    return WaterConsumption.class.getSimpleName();
  }

  @Override
  public XRepository<WaterConsumption, BigInteger> getRepository() {
    return this.waterConsumptionRepository;
  }

}
