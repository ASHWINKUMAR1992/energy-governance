package com.ashwin.energygovernance.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ashwin.energygovernance.common.exception.ResourceNotFoundException;
import com.ashwin.energygovernance.entity.ElectricityConsumption;
import com.ashwin.energygovernance.entity.Hotel;
import com.ashwin.energygovernance.entity.WasteGeneration;
import com.ashwin.energygovernance.entity.WaterConsumption;
import com.ashwin.energygovernance.mapper.EnergyGovernanceMapper;
import com.ashwin.energygovernance.service.ElectricityConsumptionService;
import com.ashwin.energygovernance.service.HotelService;
import com.ashwin.energygovernance.service.WasteGenerationService;
import com.ashwin.energygovernance.service.WaterConsumptionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EnergyGovernanceController {

  @Autowired
  private HotelService hotelService;

  @Autowired
  private ElectricityConsumptionService electricityConsumptionService;

  @Autowired
  private WaterConsumptionService waterConsumptionService;

  @Autowired
  private WasteGenerationService wasteGenerationService;

  @Autowired
  private EnergyGovernanceMapper mapper;

  @Autowired
  private KafkaTemplate<String, ?> kafkaTemplate;

  public static final Logger LOGGER = LogManager.getLogger(EnergyGovernanceController.class);

  @GetMapping(value = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Hotel>> getHotels() {
    return new ResponseEntity<>(mapper.getListOfHotelResponse(hotelService.getHotels()), HttpStatus.OK);
  }

  @GetMapping(value = "hotels/{hotelId}/{energyType}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getResourceByHotelId(@PathVariable BigInteger hotelId,
      @PathVariable String energyType) {
    LOGGER.debug("GET energyconsumption details by hotel and energyconsumptionType");
    if ("water".equals(energyType)) {
      return new ResponseEntity<>(mapper.toWaterConsumptionSetResponse(
          waterConsumptionService.findResourceByHotelId(hotelId)), HttpStatus.OK);
    } else if ("waste".equals(energyType)) {
      return new ResponseEntity<>(mapper.toWasteGenerationSetResponse(
          wasteGenerationService.findResourceByHotelId(hotelId)), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(mapper.toElectricityConsumptionSetResponse(
          electricityConsumptionService.findResourceByHotelId(hotelId)), HttpStatus.OK);
    }
  }

  @PostMapping(value = "/hotels", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createHotel(@RequestBody String hotelBody) throws ResourceNotFoundException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Hotel hotel = objectMapper.readValue(hotelBody, Hotel.class);
    hotel.setUpdated(OffsetDateTime.now());
    return new ResponseEntity<>(hotelService.insertResource(hotel),HttpStatus.CREATED);
  }

  @PostMapping(value = "/hotels/{hotelId}/{energyType}",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> createEnergyConsumptionTypeProducer(@PathVariable BigInteger hotelId,
      @PathVariable String energyType, @RequestBody String energyConsumptionObject)
      throws IOException {
    LOGGER.info("POST energyconsumption details by hotel and energyconsumptionType through kafka producer");
    ObjectMapper objectMapper = new ObjectMapper();
    Hotel hotel = new Hotel();
    hotel.setId(hotelId);
    String TOPIC = null;
    if ("water".equals(energyType)) {
      TOPIC = "WATER";
      WaterConsumption waterConsumption =
          objectMapper.readValue(energyConsumptionObject, WaterConsumption.class);
      waterConsumption.setHotel(hotel);
      kafkaTemplate
      .send(MessageBuilder.withPayload(waterConsumption).setHeader(KafkaHeaders.TOPIC, TOPIC).build());
      return new ResponseEntity<String>("Published Successfully for topic water", HttpStatus.OK);
    } else if ("waste".equals(energyType)) {
      TOPIC = "WASTE";
      WasteGeneration wasteGeneration =
          objectMapper.readValue(energyConsumptionObject, WasteGeneration.class);
      wasteGeneration.setHotel(hotel);
      kafkaTemplate
      .send(MessageBuilder.withPayload(wasteGeneration).setHeader(KafkaHeaders.TOPIC, TOPIC).build());
      return new ResponseEntity<String>("Published Successfully for topic waste", HttpStatus.OK);
    } else {
      TOPIC = "ELECTRICITY";
      ElectricityConsumption electricityConsumption =
          objectMapper.readValue(energyConsumptionObject, ElectricityConsumption.class);
      electricityConsumption.setHotel(hotel);
      kafkaTemplate
      .send(MessageBuilder.withPayload(electricityConsumption).setHeader(KafkaHeaders.TOPIC, TOPIC).build());
      return new ResponseEntity<String>("Published Successfully for topic electricity", HttpStatus.OK);
    }
  }
}
