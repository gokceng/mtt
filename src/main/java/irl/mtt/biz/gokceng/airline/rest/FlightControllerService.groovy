package irl.mtt.biz.gokceng.airline.rest

import irl.mtt.biz.gokceng.airline.model.FlightDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 02.11.2015
 */
@RestController
@RequestMapping(value = "/flights")
class FlightControllerService {
	@Autowired
	private FlightControllerRestClient flightControllerRestClient

	@RequestMapping(value = "/{originAirport}/{destinationAirport}/{departureDate}/{returnDate}/{numberOfPassengers}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Map<String, Set<FlightDto>>> getFlights(
			@PathVariable String originAirport,
			@PathVariable String destinationAirport,
			@PathVariable Date departureDate,
			@PathVariable Date returnDate,
			@PathVariable int numberOfPassengers) {
		Set<FlightDto> flightDtos = flightControllerRestClient.getFlights(originAirport, destinationAirport, departureDate, returnDate, numberOfPassengers);
		Map<String, Set<FlightDto>> singletonMap = Collections.<String, Set<FlightDto>> singletonMap("availability", flightDtos);
		ResponseEntity<Map<String, Set<FlightDto>>> re = new ResponseEntity<Map<String, Set<FlightDto>>>(singletonMap, HttpStatus.OK);
		return re;
	}
}
