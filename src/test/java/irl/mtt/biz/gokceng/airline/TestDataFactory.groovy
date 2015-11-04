package irl.mtt.biz.gokceng.airline

import irl.mtt.biz.gokceng.airline.model.*
import irl.mtt.biz.gokceng.airline.schema.model.*
import org.springframework.util.ReflectionUtils

import java.lang.reflect.Field

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 04.11.2015
 */
final class TestDataFactory {
	static final ObjectFactory objectFactory = new ObjectFactory();

	static Fare getFare(String basePrice, String fees, String tax, String clazz = null) {
		def fare = objectFactory.createFare();
		fare.basePrice = basePrice;
		fare.fees = fees;
		fare.tax = tax;
		fare.clazz = clazz;
		return fare;
	}

	static FarePriceDto getFarePriceDto(String basePrice, String fees, String tax, String currency) {
		PriceDto basePriceDto = new PriceDto(currency: currency, amount: new BigDecimal(basePrice))
		PriceDto feesDto = new PriceDto(currency: currency, amount: new BigDecimal(fees))
		PriceDto taxDto = new PriceDto(currency: currency, amount: new BigDecimal(tax))
		return new FarePriceDto(ticket: basePriceDto, bookingFee: feesDto, tax: taxDto);
	}

	static PriceDto getPriceDto(String currency, String amount) {
		return new PriceDto(currency: currency, amount: new BigDecimal(amount))
	}

	static Availability getAvailability() {
		Field flightField = ReflectionUtils.findField(Availability.class, "flight");
		ReflectionUtils.makeAccessible(flightField);
		Availability availability = objectFactory.createAvailability();
		ReflectionUtils.setField(flightField, availability, flights);
		return availability;
	}

	static List<Flight> getFlights() {
		Fares fares1 = objectFactory.createFares();
		Fare cif1 = getFare('EUR 648.00', 'EUR 81.00', 'EUR 64.80', 'CIF');
		Fare yif1 = getFare('EUR 264.00', 'EUR 16.50', 'EUR 13.20', 'YIF');
		Fare fif1 = getFare('EUR 888.00', 'EUR 55.50', 'EUR 44.40', 'FIF');
		fares1.fare << cif1;
		fares1.fare << yif1;
		fares1.fare << fif1;

		Fares fares2 = objectFactory.createFares();
		Fare cif2 = getFare('EUR 888.00', 'EUR 55.50', 'EUR 44.40', 'CIF');
		Fare yif2 = getFare('EUR 264.00', 'EUR 16.50', 'EUR 13.20', 'YIF');
		Fare fif2 = getFare('EUR 648.00', 'EUR 81.00', 'EUR 64.80', 'FIF');
		fares2.fare << cif2;
		fares2.fare << yif2;
		fares2.fare << fif2;

		List<Calendar> calendars = [];
		Calendar calendar = Calendar.getInstance()
		for (i in 0..10) {
			calendars << calendar.updated(year: 2015, month: 0, dayOfMonth: 1, hourOfDay: i, minute: 0, second: 0);
		}

		List<Flight> flightList = [];
		for (i in 1..10) {
			Flight flight = objectFactory.createFlight();
			flight.setArrivalDate(calendars[i]);
			flight.setDepartureDate(calendars[i - 1]);

			flight.setCarrierCode("CC" + i);
			flight.setFlightDesignator("FD" + i);
			flight.setDestinationAirport("DA" + i);
			flight.setOriginAirport("OA" + i);
			i % 2 == 0 ? flight.setFares(fares1) : flight.setFares(fares2);
			flightList << flight;
		}
		return flightList;
	}

	static List<FlightDtoWrapper> getFlightDtoWrapperList() {
		FarePricesDto farePricesDto1 = new FarePricesDto(first: getFarePriceDto('888.00', '55.50', '44.40', 'EUR'),
				business: getFarePriceDto('648.00', '81.00', '64.80', 'EUR'),
				economy: getFarePriceDto('264.00', '16.50', '13.20', 'EUR'))
		FarePricesDto farePricesDto2 = new FarePricesDto(first: getFarePriceDto('648.00', '81.00', '64.80', 'EUR'),
				business: getFarePriceDto('888.00', '55.50', '44.40', 'EUR'),
				economy: getFarePriceDto('264.00', '16.50', '13.20', 'EUR'))
		List<FlightDtoWrapper> flightDtoWrappers = [];
		List<TimeDto> timeDtoList = [];
		for (i in 0..10) {
			timeDtoList << new TimeDto(date: '01-01-2015', time: String.format('%02d:00AM', i))
		}
		for (i in 1..10) {
			FlightDto flight = new FlightDto(operator: "CC" + i,
					flightNumber: "FD" + i,
					departsFrom: "OA" + i,
					arrivesAt: "DA" + i,
					flightTime: '01:00',
					arrivesOn: timeDtoList[i],
					departsOn: timeDtoList[i - 1]);
			flight.farePrices = i % 2 == 0 ? farePricesDto1 : farePricesDto2;
			flightDtoWrappers << new FlightDtoWrapper(flight: flight);
		}
		return flightDtoWrappers;
	}
}
