package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.SpockApplicationTest
import irl.mtt.biz.gokceng.airline.model.TimeDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import spock.lang.Shared

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 04.11.2015
 */
class CalendarToTimeDtoConverterTest extends SpockApplicationTest {
	@Autowired
	Converter<Calendar, TimeDto> converter;

	@Shared
	Calendar calendar = Calendar.getInstance();

	def "Convert currency string to PriceDto"(Calendar source, TimeDto expected) {
		expect:
		converter.convert(source) == expected

		where:
		source                                                                             || expected
		calendar.updated(year: 2011, month: 11, dayOfMonth: 12, hourOfDay: 18, minute: 45) || new TimeDto(date: '12-12-2011', time: '18:45PM')
		calendar.updated(year: 2013, month: 0, dayOfMonth: 1, hourOfDay: 10, minute: 2)    || new TimeDto(date: '01-01-2013', time: '10:02AM')
		calendar.updated(year: 1987, month: 6, dayOfMonth: 26, hourOfDay: 9, minute: 15)   || new TimeDto(date: '26-07-1987', time: '09:15AM')
		calendar.updated(year: 2015, month: 10, dayOfMonth: 4, hourOfDay: 18, minute: 15)  || new TimeDto(date: '04-11-2015', time: '18:15PM')
	}
}
