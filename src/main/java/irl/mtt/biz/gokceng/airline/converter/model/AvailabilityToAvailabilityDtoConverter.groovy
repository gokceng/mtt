package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.converter.ConversionServiceAwareConverter
import irl.mtt.biz.gokceng.airline.model.AvailabilityDto
import irl.mtt.biz.gokceng.airline.model.FlightDto
import irl.mtt.biz.gokceng.airline.model.FlightDtoWrapper
import irl.mtt.biz.gokceng.airline.schema.model.Availability
import org.springframework.core.convert.ConversionService

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
class AvailabilityToAvailabilityDtoConverter extends ConversionServiceAwareConverter<Availability, AvailabilityDto> {
	@Override
	AvailabilityDto convert(Availability source) {
		Set<FlightDtoWrapper> flightDtoSet = new HashSet<>(source.flight.size());
		ConversionService conversionService = conversionService();
		source.flight.each { it -> flightDtoSet.add(new FlightDtoWrapper(flight: conversionService.convert(it, FlightDto.class))) };
		AvailabilityDto availabilityDto = new AvailabilityDto(flights: flightDtoSet);
		return availabilityDto;
	}
}
