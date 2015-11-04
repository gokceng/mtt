package irl.mtt.biz.gokceng.airline.converter.generic

import org.joda.time.DateTime
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
class StringToDateConverter implements Converter<String, Date> {
	private final DateTimeFormatter dateTimeFormatter

	StringToDateConverter(String format) {
		Assert.hasLength(format)
		dateTimeFormatter = DateTimeFormat.forPattern(format)
	}

	public Date convert(String source) {
		Assert.hasLength(source)
		DateTime dateTime = dateTimeFormatter.parseDateTime(source)
		return dateTime.toDate()
	}
}
