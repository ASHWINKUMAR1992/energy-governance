package com.ashwin.energygovernance.repository;

import java.math.BigInteger;
import java.util.Set;
import com.ashwin.energygovernance.common.repository.XRepository;
import com.ashwin.energygovernance.entity.WasteGeneration;

public interface WasteGenerationRepository extends XRepository<WasteGeneration,BigInteger>{

  Set<WasteGeneration> findByHotelId(BigInteger id);
}
