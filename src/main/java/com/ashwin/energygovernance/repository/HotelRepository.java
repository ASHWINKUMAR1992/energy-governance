/**
 * 
 */
package com.ashwin.energygovernance.repository;

import java.math.BigInteger;
import org.springframework.stereotype.Repository;
import com.ashwin.energygovernance.common.repository.XRepository;
import com.ashwin.energygovernance.entity.Hotel;

/**
 * @author ashwin
 * @param <E>
 *
 */
@Repository
public interface HotelRepository extends XRepository<Hotel,BigInteger> {

}
