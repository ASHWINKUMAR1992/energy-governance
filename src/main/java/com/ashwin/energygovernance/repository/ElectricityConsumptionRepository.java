package com.ashwin.energygovernance.repository;

import java.math.BigInteger;
import java.util.Set;
import com.ashwin.energygovernance.common.repository.XRepository;
import com.ashwin.energygovernance.entity.ElectricityConsumption;

public interface ElectricityConsumptionRepository extends XRepository<ElectricityConsumption,BigInteger> {

  Set<ElectricityConsumption> findByHotelId(BigInteger id);
}
