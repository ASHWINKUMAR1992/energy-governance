package com.ashwin.energygovernance.repository;

import java.math.BigInteger;
import java.util.Set;
import com.ashwin.energygovernance.common.repository.XRepository;
import com.ashwin.energygovernance.entity.WaterConsumption;

public interface WaterConsumptionRepository extends XRepository<WaterConsumption,BigInteger> {

  Set<WaterConsumption> findByHotelId(BigInteger id);
}
