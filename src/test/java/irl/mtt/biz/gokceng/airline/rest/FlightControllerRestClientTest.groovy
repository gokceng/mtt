package irl.mtt.biz.gokceng.airline.rest

import irl.mtt.biz.gokceng.airline.GokcengApplicationTests
import irl.mtt.biz.gokceng.airline.model.AvailabilityDto
import irl.mtt.biz.gokceng.airline.schema.model.Availability
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

import static irl.mtt.biz.gokceng.airline.TestDataFactory.getAvailability
import static irl.mtt.biz.gokceng.airline.TestDataFactory.getFlightDtoWrapperList
import static org.mockito.Matchers.*

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 04.11.2015
 */
class FlightControllerRestClientTest extends GokcengApplicationTests {
	@Autowired
	@InjectMocks
	private FlightControllerRestClient flightControllerRestClient;

	@Mock
	private RestTemplate restTemplate;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);

		def responseEntity = new ResponseEntity<>(getAvailability(), HttpStatus.OK)
		Mockito.when(restTemplate.getForEntity(anyString(), eq(Availability.class), anyString(), anyString(), anyString(), anyString(), anyInt())).thenReturn(responseEntity);
	}

	@Test
	void testGetFlights() {
		def instance = Calendar.getInstance()
		AvailabilityDto availabilityDto = flightControllerRestClient.getFlights("IST", "DUB", instance.getTime(), instance.updated(year: 2015, month: 11, dayOfMonth: 15).getTime(), 1);
		AvailabilityDto expectedAvailabilityDto = new AvailabilityDto(flights: getFlightDtoWrapperList())
		Assert.assertEquals(availabilityDto, expectedAvailabilityDto);
	}
}
