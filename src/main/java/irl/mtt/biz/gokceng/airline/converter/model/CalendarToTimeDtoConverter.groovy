package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.converter.ConversionServiceAwareConverter
import irl.mtt.biz.gokceng.airline.model.TimeDto
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.springframework.util.Assert

import javax.annotation.concurrent.ThreadSafe

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
@ThreadSafe
class CalendarToTimeDtoConverter extends ConversionServiceAwareConverter<Calendar, TimeDto> {
	private final DateTimeFormatter dateFormatter
	private final DateTimeFormatter timeFormatter

	CalendarToTimeDtoConverter(String dateFormat, String timeFormat) {
		Assert.hasLength(dateFormat)
		Assert.hasLength(timeFormat)
		dateFormatter = DateTimeFormat.forPattern(dateFormat);
		timeFormatter = DateTimeFormat.forPattern(timeFormat);
	}

	@Override
	TimeDto convert(Calendar source) {
		Assert.notNull(source);

		long sourceTimeInMillis = source.getTime().getTime()
		String date = dateFormatter.print(sourceTimeInMillis);
		String time = timeFormatter.print(sourceTimeInMillis);
		return new TimeDto(date: date, time: time);
	}
}
