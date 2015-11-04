package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.SpockApplicationTest
import irl.mtt.biz.gokceng.airline.model.FarePriceDto
import irl.mtt.biz.gokceng.airline.schema.model.Fare
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter

import static irl.mtt.biz.gokceng.airline.TestDataFactory.getFare
import static irl.mtt.biz.gokceng.airline.TestDataFactory.getFarePriceDto

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 04.11.2015
 */
class FareToFarePriceDtoConverterTest extends SpockApplicationTest {
	@Autowired
	Converter<Fare, FarePriceDto> converter;

	def "Convert Fare to FarePrice"(Fare source, FarePriceDto expected) {
		expect:
		converter.convert(source) == expected

		where:
		source                                          || expected
		getFare('EUR 648.00', 'EUR 81.00', 'EUR 64.80') || getFarePriceDto('648.00', '81.00', '64.80', 'EUR')
		getFare('EUR 264.00', 'EUR 16.50', 'EUR 13.20') || getFarePriceDto('264.00', '16.50', '13.20', 'EUR')
		getFare('TRY 180.00', 'TRY 11.25', 'TRY 9.00')  || getFarePriceDto('180.00', '11.25', '9.00', 'TRY')
		getFare('EUR 888.00', 'EUR 55.50', 'EUR 44.40') || getFarePriceDto('888.00', '55.50', '44.40', 'EUR')
	}
}
