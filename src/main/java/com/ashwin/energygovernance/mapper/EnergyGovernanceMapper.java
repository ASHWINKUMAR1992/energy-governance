package com.ashwin.energygovernance.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import com.ashwin.energygovernance.entity.ElectricityConsumption;
import com.ashwin.energygovernance.entity.Hotel;
import com.ashwin.energygovernance.entity.WasteGeneration;
import com.ashwin.energygovernance.entity.WaterConsumption;

@Component
public class EnergyGovernanceMapper {
  
  public Hotel toHotelResponse(Hotel hotelEntity) {
    Hotel response = new Hotel();
    response.setId(hotelEntity.getId());
    response.setHotelName(hotelEntity.getHotelName());
    if(hotelEntity.getHotelCode() != null) 
      response.setHotelCode(hotelEntity.getHotelCode());
    response.setUpdated(hotelEntity.getUpdated());

    Set<ElectricityConsumption> ecSet = new HashSet<>();
    for(ElectricityConsumption ec : hotelEntity.getElectricityConsumptionSet()) {
      ecSet.add(toElectricityConsumptionResponse(ec));
    }
    response.setElectricityConsumptionSet(toElectricityConsumptionSetResponse(hotelEntity.getElectricityConsumptionSet()));

    response.setWasteGenerationSet(toWasteGenerationSetResponse(hotelEntity.getWasteGenerationSet()));

    response.setWaterConsumptionSet(toWaterConsumptionSetResponse(hotelEntity.getWaterConsumptionSet()));

    return response;
  }

  public WasteGeneration toWasteGenerationResponse(WasteGeneration wg) {
    WasteGeneration wasteGeneration = new WasteGeneration();
    wasteGeneration.setId(wg.getId());
    wasteGeneration.setQuantity(wg.getQuantity());
    wasteGeneration.setUpdated(wg.getUpdated());
    return wasteGeneration;
  }

  public WaterConsumption toWaterConsumptionResponse(WaterConsumption wc) {
    WaterConsumption waterConsumption = new WaterConsumption();
    waterConsumption.setId(wc.getId());
    waterConsumption.setQuantity(wc.getQuantity());
    waterConsumption.setUpdated(wc.getUpdated());
    return waterConsumption;
  }

  public ElectricityConsumption toElectricityConsumptionResponse(ElectricityConsumption ec) {
    ElectricityConsumption electricityConsumption = new ElectricityConsumption();
    electricityConsumption.setId(ec.getId());
    electricityConsumption.setQuantity(ec.getQuantity());
    electricityConsumption.setUpdated(ec.getUpdated());
    return electricityConsumption;
  }

  public List<Hotel> getListOfHotelResponse(List<Hotel> hotelEntityList) {
    List<Hotel> hotelListResponse = new ArrayList<>();
    for(Hotel hotel : hotelEntityList) {
      hotelListResponse.add(toHotelResponse(hotel));
    }
    return hotelListResponse;
  }

  public Set<ElectricityConsumption> toElectricityConsumptionSetResponse(Set<ElectricityConsumption> electricityConsumptions) {
    Set<ElectricityConsumption> electricityConsumptionSet = new HashSet<>();
    for(ElectricityConsumption ec : electricityConsumptions) {
      electricityConsumptionSet.add(toElectricityConsumptionResponse(ec));
    }
    return electricityConsumptionSet;
  }

  public Set<WaterConsumption> toWaterConsumptionSetResponse(Set<WaterConsumption> waterConsumptions) {
    Set<WaterConsumption> waterConsumptionSet = new HashSet<>();
    for(WaterConsumption wc : waterConsumptions) {
      waterConsumptionSet.add(toWaterConsumptionResponse(wc));
    }
    return waterConsumptionSet;
  }

  public Set<WasteGeneration> toWasteGenerationSetResponse(Set<WasteGeneration> wasteGenerations) {
    Set<WasteGeneration> wasteGenerationSet = new HashSet<>();
    for(WasteGeneration wg : wasteGenerations) {
      wasteGenerationSet.add(toWasteGenerationResponse(wg));
    }
    return wasteGenerationSet;
  }
}
