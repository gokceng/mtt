package irl.mtt.biz.gokceng.airline.converter.generic

import irl.mtt.biz.gokceng.airline.SpockApplicationTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 04.11.2015
 */
class StringToDateConverterTest extends SpockApplicationTest {
	@Autowired
	Converter<String, Date> converter;

	def "Convert String in yyyyMMdd format to Date"(String source, Date expected) {
		expect:
		converter.convert(source) == expected

		where:
		source     || expected
		'20151206' || new Date(115, 11, 6)
		'20000201' || new Date(100, 1, 1)
		'19910201' || new Date(91, 1, 1)
		'19870726' || new Date(87, 6, 26)
	}
}
