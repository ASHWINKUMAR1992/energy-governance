package com.ashwin.energygovernance.entity;

import static org.junit.Assert.assertTrue;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import javax.validation.Validation;
import org.force66.beantester.BeanTester;
import org.junit.jupiter.api.Test;

public class HotelTest {

  @Test
  void testForBeanTester() {
    List<String> excludedFieldsList = Arrays.asList(new String[] {"updated"});
    BeanTester beanTester = new BeanTester();
    for (String field : excludedFieldsList) {
      beanTester.addExcludedField(field);
    }
    beanTester.testBean(Hotel.class);
  }

  /**
   * Test Customer for Phone scenario.
   */
  @Test
  void testForValidationSuccess() {
    Hotel hotel = new Hotel();
    hotel.setId(BigInteger.valueOf(1));
    hotel.setHotelName("Hotel1");
    hotel.setHotelCode("HOT1");
    hotel.setUpdated();
    assertTrue(Validation.buildDefaultValidatorFactory().getValidator().validate(hotel).isEmpty());
  }
}
