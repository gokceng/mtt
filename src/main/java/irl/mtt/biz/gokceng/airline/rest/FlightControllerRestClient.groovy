package irl.mtt.biz.gokceng.airline.rest

import irl.mtt.biz.gokceng.airline.model.FlightDto
import irl.mtt.biz.gokceng.airline.schema.model.Availability
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.ConversionService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
@Service
class FlightControllerRestClient {
	private static
	final String ENDPOINT = "http://private-72732-mockairline.apiary-mock.com/flights/{originAirport}/{destinationAirport}/{departureDate}/{returnDate}/{numberOfPassengers}";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	ConversionService conversionService;

	Set<FlightDto> getFlights(String originAirport,
	                          String destinationAirport,
	                          Date departureDate,
	                          Date returnDate,
	                          int numberOfPassengers) {

		String departureDateStr = conversionService.convert(departureDate, String.class);
		String returnDateStr = conversionService.convert(returnDate, String.class);
		ResponseEntity<Availability> responseEntity = restTemplate.getForEntity(ENDPOINT, Availability.class, originAirport, destinationAirport, departureDateStr, returnDateStr, numberOfPassengers);

		Set<FlightDto> flightDtos = conversionService.convert(responseEntity.body, Set.class);
		return flightDtos;
	}
}
