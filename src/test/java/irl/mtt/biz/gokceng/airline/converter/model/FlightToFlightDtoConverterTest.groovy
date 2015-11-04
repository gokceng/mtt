package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.SpockApplicationTest
import irl.mtt.biz.gokceng.airline.model.FlightDto
import irl.mtt.biz.gokceng.airline.schema.model.Flight
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import spock.lang.Shared

import static irl.mtt.biz.gokceng.airline.TestDataFactory.getFlightDtoWrapperList
import static irl.mtt.biz.gokceng.airline.TestDataFactory.getFlights

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 04.11.2015
 */
class FlightToFlightDtoConverterTest extends SpockApplicationTest {
	@Autowired
	Converter<Flight, FlightDto> converter;

	@Autowired
	FaresToFarePricesDtoConverter farePricesDtoConverter;

	@Shared
	List<Flight> flightList = [];
	@Shared
	List<FlightDto> flightDtoList = [];

	void setupSpec() {
		flightList.addAll(getFlights())
		getFlightDtoWrapperList().each { it -> flightDtoList << it.flight }
	}

	def "Convert Flight to FlightDto"(int i, Flight source, FlightDto expected) {
		expect:
		converter.convert(source) == expected

		where:
		i << (0..9)
		source = flightList[i]
		expected = flightDtoList[i]
	}
}
