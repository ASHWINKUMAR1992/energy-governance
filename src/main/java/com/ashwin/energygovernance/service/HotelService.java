/**
 * 
 */
package com.ashwin.energygovernance.service;

import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ashwin.energygovernance.common.repository.XRepository;
import com.ashwin.energygovernance.common.service.AbstractService;
import com.ashwin.energygovernance.entity.Hotel;
import com.ashwin.energygovernance.repository.HotelRepository;

/**
 * @author ashwin
 * @param <E>
 *
 */
@Service
public class HotelService extends AbstractService<Hotel, BigInteger> {

  private HotelRepository hotelRepository;

  public List<Hotel> getHotels() {
     return hotelRepository.findAll();
  }

  /**
   * 
   * @return String
   */
  @Override
  protected String getEntityName() {
    return Hotel.class.getSimpleName();
  }

  @Override
  public XRepository<Hotel, BigInteger> getRepository() {
    return this.hotelRepository;
  }

  /**
   * 
   * @param repository
   */
  @Autowired
  public HotelService(HotelRepository repository) {
    this.hotelRepository = repository;
  }
}
