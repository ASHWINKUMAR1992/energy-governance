package com.ashwin.energygovernance.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.ashwin.energygovernance.entity.Hotel;
import com.ashwin.energygovernance.mapper.EnergyGovernanceMapper;
import com.ashwin.energygovernance.service.HotelService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.path.json.JsonPath;

public class EnergyGovernanceControllerTest {

  EnergyGovernanceController controller;
  EnergyGovernanceMapper mapper;
  HotelService service;

  @Test
  public void testGetHotels() {
    service = mock(HotelService.class);
    mapper = mock(EnergyGovernanceMapper.class);
    MockMvcRequestSpecification spec = getRawSpec(controller);
    Mockito.when(service.getHotels()).thenReturn(getListOfHotels());
    Mockito.when(mapper.getListOfHotelResponse(getListOfHotels())).thenReturn(getListOfHotels());
    spec.contentType(ContentType.JSON).when().get("/hotels").then()
    .body("", equalTo(new JsonPath(getListOfHotels().toString()).getJsonObject("")))
    .statusCode(200);
  }

  private List<Hotel> getListOfHotels() {
    List<Hotel> hotels= new ArrayList<>();
    Hotel h1 = new Hotel(BigInteger.valueOf(1),"Hotel 1", "HOT1");
    Hotel h2 = new Hotel(BigInteger.valueOf(2),"Hotel 2", "HOT2");
    hotels.add(h1);
    hotels.add(h2);
    return hotels;
  }

  protected MockMvcRequestSpecification getRawSpec(final Object... beans) {
    return given().standaloneSetup(beans);
  }
}
