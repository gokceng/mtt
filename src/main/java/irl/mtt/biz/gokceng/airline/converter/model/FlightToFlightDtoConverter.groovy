package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.converter.ConversionServiceAwareConverter
import irl.mtt.biz.gokceng.airline.model.FarePricesDto
import irl.mtt.biz.gokceng.airline.model.FlightDto
import irl.mtt.biz.gokceng.airline.model.TimeDto
import irl.mtt.biz.gokceng.airline.schema.model.Flight
import org.springframework.util.Assert

import javax.annotation.concurrent.ThreadSafe
import java.util.concurrent.TimeUnit

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
@ThreadSafe
class FlightToFlightDtoConverter extends ConversionServiceAwareConverter<Flight, FlightDto> {
	@Override
	FlightDto convert(Flight source) {
		FlightDto flightDto = new FlightDto();
		flightDto.operator = source.carrierCode;
		flightDto.flightNumber = source.flightDesignator;
		flightDto.departsFrom = source.originAirport;
		flightDto.arrivesAt = source.destinationAirport;
		flightDto.flightTime = getFlightTime(source.departureDate, source.arrivalDate);

		flightDto.departsOn = convertToType(source.departureDate, TimeDto.class);
		flightDto.arrivesOn = convertToType(source.arrivalDate, TimeDto.class);
		flightDto.farePrices = convertToType(source.fares, FarePricesDto.class);
		return flightDto;
	}

	private static String getFlightTime(Calendar startTime, Calendar endTime) {
		Assert.notNull(startTime)
		Assert.notNull(endTime)

		long diff = endTime.getTimeInMillis() - startTime.getTimeInMillis();
		long hours = TimeUnit.MILLISECONDS.toHours(diff);
		diff -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
		return String.format("%02d:%02d", hours, minutes);
	}
}
