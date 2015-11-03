package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.converter.ConversionServiceAwareConverter
import irl.mtt.biz.gokceng.airline.model.FlightDto
import irl.mtt.biz.gokceng.airline.schema.model.Availability
import irl.mtt.biz.gokceng.airline.schema.model.Flight
import org.springframework.core.convert.ConversionService

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
class AvailabilityToFlightSetConverter extends ConversionServiceAwareConverter<Availability, Set<Flight>> {
	@Override
	Set<Flight> convert(Availability source) {
		Set<FlightDto> flightDtoSet = new HashSet<>(source.flight.size());
		ConversionService conversionService = conversionService();
		source.flight.each { it -> flightDtoSet.add(conversionService.convert(it, FlightDto.class)) };
		return flightDtoSet;
	}
}
