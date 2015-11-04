package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.SpockApplicationTest
import irl.mtt.biz.gokceng.airline.model.FarePriceDto
import irl.mtt.biz.gokceng.airline.model.FarePricesDto
import irl.mtt.biz.gokceng.airline.schema.model.Fare
import irl.mtt.biz.gokceng.airline.schema.model.Fares
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared

import static irl.mtt.biz.gokceng.airline.TestDataFactory.getFare
import static irl.mtt.biz.gokceng.airline.TestDataFactory.getFarePriceDto

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 04.11.2015
 */
class FaresToFarePricesDtoConverterTest extends SpockApplicationTest {
	@Autowired
	FaresToFarePricesDtoConverter converter;

	@Shared
	Fares fares1 = objectFactory.createFares();
	@Shared
	Fares fares2 = objectFactory.createFares();

	void setup() {
		Fare cif1 = getFare('EUR 648.00', 'EUR 81.00', 'EUR 64.80', 'CIF');
		Fare yif1 = getFare('EUR 264.00', 'EUR 16.50', 'EUR 13.20', 'YIF');
		Fare fif1 = getFare('EUR 888.00', 'EUR 55.50', 'EUR 44.40', 'FIF');
		fares1.fare << cif1;
		fares1.fare << yif1;
		fares1.fare << fif1;

		Fare cif2 = getFare('EUR 888.00', 'EUR 55.50', 'EUR 44.40', 'CIF');
		Fare yif2 = getFare('EUR 264.00', 'EUR 16.50', 'EUR 13.20', 'YIF');
		Fare fif2 = getFare('EUR 648.00', 'EUR 81.00', 'EUR 64.80', 'FIF');
		fares2.fare << cif2;
		fares2.fare << yif2;
		fares2.fare << fif2;
	}

	def "Convert Fares to FarePricesDto"(Fares source, FarePricesDto expected) {
		expect:
		converter.convert(source) == expected

		where:
		source || expected
		fares1 || new FarePricesDto(first: getFarePriceDto('888.00', '55.50', '44.40', 'EUR'),
				business: getFarePriceDto('648.00', '81.00', '64.80', 'EUR'),
				economy: getFarePriceDto('264.00', '16.50', '13.20', 'EUR'))
		fares2 || new FarePricesDto(first: getFarePriceDto('648.00', '81.00', '64.80', 'EUR'),
				business: getFarePriceDto('888.00', '55.50', '44.40', 'EUR'),
				economy: getFarePriceDto('264.00', '16.50', '13.20', 'EUR'))
	}

	def "GetFarePriceDtoForFareType"(List<Fare> source, String fareType, FarePriceDto expected) {
		expect:
		converter.getFarePriceDto(source, fareType) == expected

		where:
		source      | fareType || expected
		fares1.fare | 'CIF'    || getFarePriceDto('648.00', '81.00', '64.80', 'EUR')
		fares1.fare | 'YIF'    || getFarePriceDto('264.00', '16.50', '13.20', 'EUR')
		fares1.fare | 'FIF'    || getFarePriceDto('888.00', '55.50', '44.40', 'EUR')
		fares2.fare | 'FIF'    || getFarePriceDto('648.00', '81.00', '64.80', 'EUR')
		fares2.fare | 'YIF'    || getFarePriceDto('264.00', '16.50', '13.20', 'EUR')
		fares2.fare | 'CIF'    || getFarePriceDto('888.00', '55.50', '44.40', 'EUR')
	}

	def "Get FarePriceDto not included in source"(List<Fare> source, String fareType, FarePriceDto expected) {
		expect:
		converter.getFarePriceDto(source, fareType) == expected

		where:
		source      | fareType || expected
		fares1.fare | 'XXX'    || null
		fares2.fare | 'YYY'    || null
	}
}
