package com.ashwin.energygovernance.service;

import java.math.BigInteger;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ashwin.energygovernance.common.repository.XRepository;
import com.ashwin.energygovernance.common.service.AbstractService;
import com.ashwin.energygovernance.entity.ElectricityConsumption;
import com.ashwin.energygovernance.repository.ElectricityConsumptionRepository;

@Service
public class ElectricityConsumptionService extends AbstractService<ElectricityConsumption, BigInteger> {

  private ElectricityConsumptionRepository electricityConsumptionRepository;

  public Set<ElectricityConsumption> findResourceByHotelId(BigInteger hotelId) {
    return electricityConsumptionRepository.findByHotelId(hotelId);
  }

  @Override
  protected String getEntityName() {
    return ElectricityConsumption.class.getSimpleName();
  }

  @Override
  public XRepository<ElectricityConsumption, BigInteger> getRepository() {
    return this.electricityConsumptionRepository;
  }

  /**
   * 
   * @param repository
   */
  @Autowired
  public ElectricityConsumptionService(ElectricityConsumptionRepository repository) {
    this.electricityConsumptionRepository = repository;
  }
}
