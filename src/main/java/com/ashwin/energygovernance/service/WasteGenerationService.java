package com.ashwin.energygovernance.service;

import java.math.BigInteger;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ashwin.energygovernance.common.repository.XRepository;
import com.ashwin.energygovernance.common.service.AbstractService;
import com.ashwin.energygovernance.entity.WasteGeneration;
import com.ashwin.energygovernance.repository.WasteGenerationRepository;

@Service
public class WasteGenerationService extends AbstractService<WasteGeneration, BigInteger> {

  @Autowired
  private WasteGenerationRepository wasteGenerationRepository;

  public Set<WasteGeneration> findResourceByHotelId(BigInteger hotelId) {
    return wasteGenerationRepository.findByHotelId(hotelId);
  }

  @Override
  protected String getEntityName() {
    return WasteGeneration.class.getSimpleName();
  }

  @Override
  public XRepository<WasteGeneration, BigInteger> getRepository() {
    return this.wasteGenerationRepository;
  }

}
