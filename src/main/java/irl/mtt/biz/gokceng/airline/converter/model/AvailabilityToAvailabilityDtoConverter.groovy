package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.converter.ConversionServiceAwareConverter
import irl.mtt.biz.gokceng.airline.model.AvailabilityDto
import irl.mtt.biz.gokceng.airline.model.FlightDto
import irl.mtt.biz.gokceng.airline.model.FlightDtoWrapper
import irl.mtt.biz.gokceng.airline.schema.model.Availability
import org.springframework.util.Assert

import javax.annotation.concurrent.ThreadSafe

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
@ThreadSafe
class AvailabilityToAvailabilityDtoConverter extends ConversionServiceAwareConverter<Availability, AvailabilityDto> {
	@Override
	AvailabilityDto convert(Availability source) {
		Assert.notNull(source);

		List<FlightDtoWrapper> flightDtoWrappers = new ArrayList<>(source.flight.size());
		AvailabilityDto availabilityDto = new AvailabilityDto(flights: flightDtoWrappers);
		if (source.flight.empty) {
			return availabilityDto;
		}

		source.flight.each { it -> availabilityDto.flights << new FlightDtoWrapper(flight: convertToType(it, FlightDto.class)) };
		return availabilityDto;
	}
}
