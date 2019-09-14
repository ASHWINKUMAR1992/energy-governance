package com.ashwin.energygovernance.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "water_consumption", schema = "hotels")
public class WaterConsumption extends TableEntity<BigInteger> implements Serializable {

  private static final long serialVersionUID = 4899636126507845217L;

  @Id
  @Column(name = "id", updatable = false)
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private BigInteger id;

  @Column(name = "quantity")
  private String quantity;

  @ManyToOne(optional = false)
  @JoinColumn(name = "hotel_id", nullable = false)
  private Hotel hotel;

  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public Hotel getHotel() {
    return hotel;
  }

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }

}
