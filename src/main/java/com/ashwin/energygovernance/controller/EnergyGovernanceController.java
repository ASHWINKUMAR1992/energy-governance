package com.ashwin.energygovernance.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
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

  @GetMapping(value = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Hotel>> getHotels() {
    return new ResponseEntity<>(mapper.getListOfHotelResponse(hotelService.getHotels()), HttpStatus.OK);
  }

  @GetMapping(value = "hotels/{hotelId}/{energyConsumptionType}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getResourceByHotelId(@PathVariable BigInteger hotelId,
      @PathVariable String energyConsumptionType) {
    if ("water".equals(energyConsumptionType)) {
      return new ResponseEntity<>(mapper.toWaterConsumptionSetResponse(
          waterConsumptionService.findResourceByHotelId(hotelId)), HttpStatus.OK);
    } else if ("waste".equals(energyConsumptionType)) {
      return new ResponseEntity<>(mapper.toWasteGenerationSetResponse(
          wasteGenerationService.findResourceByHotelId(hotelId)), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(mapper.toElectricityConsumptionSetResponse(
          electricityConsumptionService.findResourceByHotelId(hotelId)), HttpStatus.OK);
    }
  }

  @PostMapping(value = "/hotels/{hotelId}/{energyConsumptionType}",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createEnergyConsumptionType(@PathVariable BigInteger hotelId,
      @PathVariable String energyConsumptionType, @RequestBody String energyConsumptionObject)
      throws IOException, ResourceNotFoundException {
    ObjectMapper objectMapper = new ObjectMapper();
    Hotel hotel = new Hotel();
    hotel.setId(hotelId);
    if ("water".equals(energyConsumptionType)) {
      WaterConsumption waterConsumption =
          objectMapper.readValue(energyConsumptionObject, WaterConsumption.class);
      waterConsumption.setHotel(hotel);
      waterConsumption.setUpdated(OffsetDateTime.now());
      return new ResponseEntity<>(mapper.toWaterConsumptionResponse(
          waterConsumptionService.insertResource(waterConsumption)), HttpStatus.CREATED);
    } else if ("waste".equals(energyConsumptionType)) {
      WasteGeneration wasteGeneration =
          objectMapper.readValue(energyConsumptionObject, WasteGeneration.class);
      wasteGeneration.setHotel(hotel);
      wasteGeneration.setUpdated(OffsetDateTime.now());
      return new ResponseEntity<>(
          mapper.toWasteGenerationResponse(wasteGenerationService.insertResource(wasteGeneration)),
          HttpStatus.CREATED);
    } else {
      ElectricityConsumption electricityConsumption =
          objectMapper.readValue(energyConsumptionObject, ElectricityConsumption.class);
      electricityConsumption.setHotel(hotel);
      electricityConsumption.setUpdated(OffsetDateTime.now());
      return new ResponseEntity<>(
          mapper.toElectricityConsumptionResponse(
              electricityConsumptionService.insertResource(electricityConsumption)),
          HttpStatus.CREATED);
    }
  }

  @PostMapping(value = "/hotels", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createHotel(@RequestBody String hotelBody) throws ResourceNotFoundException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Hotel hotel = objectMapper.readValue(hotelBody, Hotel.class);
    hotel.setUpdated(OffsetDateTime.now());
    return new ResponseEntity<>(hotelService.insertResource(hotel),HttpStatus.CREATED);
  }
}
