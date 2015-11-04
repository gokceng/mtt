package irl.mtt.biz.gokceng.airline.converter.generic

import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.springframework.core.convert.converter.Converter
import org.springframework.util.Assert

import javax.annotation.concurrent.ThreadSafe

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
@ThreadSafe
class DateToStringConverter implements Converter<Date, String> {
	private final DateTimeFormatter dateTimeFormatter

	DateToStringConverter(String format) {
		Assert.hasText(format)
		dateTimeFormatter = DateTimeFormat.forPattern(format)
	}

	public String convert(Date source) {
		Assert.notNull(source)
		return dateTimeFormatter.print(source.getTime())
	}
}
