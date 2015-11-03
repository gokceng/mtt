package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.converter.ConversionServiceAwareConverter
import irl.mtt.biz.gokceng.airline.model.FarePricesDto
import irl.mtt.biz.gokceng.airline.model.FlightDto
import irl.mtt.biz.gokceng.airline.model.TimeDto
import irl.mtt.biz.gokceng.airline.schema.model.Flight
import org.springframework.core.convert.ConversionService

import java.util.concurrent.TimeUnit

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
class FlightToFlightDtoConverter extends ConversionServiceAwareConverter<Flight, FlightDto> {
	@Override
	FlightDto convert(Flight source) {
		FlightDto flightDto = new FlightDto();
		ConversionService conversionService = conversionService();
		flightDto.operator = source.carrierCode;
		flightDto.flightNumber = source.flightDesignator;
		flightDto.departsFrom = source.originAirport;
		flightDto.arrivesAt = source.destinationAirport;
		flightDto.departsOn = conversionService.convert(source.departureDate, TimeDto.class);
		flightDto.arrivesOn = conversionService.convert(source.arrivalDate, TimeDto.class);
		flightDto.flightTime = getFlightTime(source.departureDate.getTimeInMillis(), source.arrivalDate.getTimeInMillis());
		flightDto.farePrices = conversionService.convert(source.fares, FarePricesDto.class);
		return flightDto;
	}

	private static String getFlightTime(long startTime, long endTime) {
		long diff = endTime - startTime;
		long hours = TimeUnit.MILLISECONDS.toHours(diff);
		diff -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
		return String.format("%02d:%02d", hours, minutes);
	}
}
