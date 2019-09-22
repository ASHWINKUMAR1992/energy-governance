package com.ashwin.energygovernance.listener;

import java.time.OffsetDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.ashwin.energygovernance.common.exception.ResourceNotFoundException;
import com.ashwin.energygovernance.entity.ElectricityConsumption;
import com.ashwin.energygovernance.entity.WasteGeneration;
import com.ashwin.energygovernance.entity.WaterConsumption;
import com.ashwin.energygovernance.service.ElectricityConsumptionService;
import com.ashwin.energygovernance.service.WasteGenerationService;
import com.ashwin.energygovernance.service.WaterConsumptionService;

@Service
public class KafkaEventConsumer {
  
  @Autowired
  WaterConsumptionService waterConsumptionService;

  @Autowired
  private WasteGenerationService wasteGenerationService;

  @Autowired
  private ElectricityConsumptionService electricityConsumptionService;

  public static final Logger LOGGER = LogManager.getLogger(KafkaEventConsumer.class);

  @KafkaListener(topics = "WATER", groupId="group_id")
  public void consumeWaterConsumptionTopic(WaterConsumption waterConsumption) throws ResourceNotFoundException {
    LOGGER.info("POST water consumption details by hotel through kafka consumer");
    waterConsumption.setUpdated(OffsetDateTime.now());
    waterConsumptionService.insertResource(waterConsumption);
  }

  @KafkaListener(topics = "ELECTRICITY", groupId="group_id")
  public void consumeElectricityConsumptionTopic(ElectricityConsumption electricityConsumption) throws ResourceNotFoundException {
    LOGGER.info("POST electricity consumption details by hotel through kafka consumer");
    electricityConsumption.setUpdated(OffsetDateTime.now());
    electricityConsumptionService.insertResource(electricityConsumption);
  }

  @KafkaListener(topics = "WASTE", groupId="group_id")
  public void consumeWasteGenerationTopic(WasteGeneration wasteGeneration) throws ResourceNotFoundException {
    LOGGER.info("POST waste generation details by hotel through kafka consumer");
    wasteGeneration.setUpdated(OffsetDateTime.now());
    wasteGenerationService.insertResource(wasteGeneration);
  }
}
