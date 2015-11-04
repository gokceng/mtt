package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.SpockApplicationTest
import irl.mtt.biz.gokceng.airline.model.PriceDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter

import static irl.mtt.biz.gokceng.airline.TestDataFactory.getPriceDto

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 04.11.2015
 */
class StringToPriceDtoConverterTest extends SpockApplicationTest {
	@Autowired
	Converter<String, PriceDto> converter;

	def "Convert currency string to PriceDto"(String source, PriceDto expected) {
		expect:
		converter.convert(source) == expected

		where:
		source       || expected
		'EUR 90.19'  || getPriceDto('EUR', '90.19')
		'EUR 360.22' || getPriceDto('EUR', '360.22')
		'EUR 324.57' || getPriceDto('EUR', '324.57')
		'TRY 324.13' || getPriceDto('TRY', '324.13')
		'TRY 98.87'  || getPriceDto('TRY', '98.87')
		'TRY 1.05'   || getPriceDto('TRY', '1.05')
	}
}
