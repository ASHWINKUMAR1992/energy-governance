package com.ashwin.energygovernance.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "hotels", schema = "hotels")
public class Hotel extends TableEntity<BigInteger> implements Serializable {

  private static final long serialVersionUID = -7668129891599319389L;

  @Id
  @Column(name = "id", updatable = false)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "hotels.hotels_id_seq")
  private BigInteger id;

  @Column(name = "hotelname")
  private String hotelName;

  @Column(name = "hotelcode")
  private String hotelCode;

  @Column(name="updated")
  private OffsetDateTime updated;

  @JsonIgnore
  @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
  private Set<WaterConsumption> waterConsumptionSet;

  @JsonIgnore
  @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
  private Set<WasteGeneration> wasteGenerationSet;

  @JsonIgnore
  @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
  private Set<ElectricityConsumption> electricityConsumptionSet;

  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  public String getHotelName() {
    return hotelName;
  }

  public void setHotelName(String hotelName) {
    this.hotelName = hotelName;
  }

  public String getHotelCode() {
    return hotelCode;
  }

  public void setHotelCode(String hotelCode) {
    this.hotelCode = hotelCode;
  }

  public OffsetDateTime getUpdated() {
    return updated;
  }

  public void setUpdated() {
    this.updated = OffsetDateTime.now();
  }

  public void setUpdated(OffsetDateTime updated) {
    this.updated = updated;
  }

  public Set<WaterConsumption> getWaterConsumptionSet() {
    return waterConsumptionSet;
  }

  public void setWaterConsumptionSet(Set<WaterConsumption> waterConsumptionSet) {
    this.waterConsumptionSet = waterConsumptionSet;
  }

  public Set<WasteGeneration> getWasteGenerationSet() {
    return wasteGenerationSet;
  }

  public void setWasteGenerationSet(Set<WasteGeneration> wasteGenerationSet) {
    this.wasteGenerationSet = wasteGenerationSet;
  }

  public Set<ElectricityConsumption> getElectricityConsumptionSet() {
    return electricityConsumptionSet;
  }

  public void setElectricityConsumptionSet(Set<ElectricityConsumption> electricityConsumptionSet) {
    this.electricityConsumptionSet = electricityConsumptionSet;
  }

  public Hotel(BigInteger id, String hotelName, String hotelCode) {
    super();
    this.id = id;
    this.hotelName = hotelName;
    this.hotelCode = hotelCode;
  }

  public Hotel() {
  }

}
