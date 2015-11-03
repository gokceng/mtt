package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.converter.ConversionServiceAwareConverter
import irl.mtt.biz.gokceng.airline.converter.CustomConverter
import irl.mtt.biz.gokceng.airline.model.TimeDto

import java.text.Format
import java.text.SimpleDateFormat

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
class CalendarToTimeDtoConverter extends ConversionServiceAwareConverter<Calendar, TimeDto> implements CustomConverter {
	@Override
	TimeDto convert(Calendar source) {
		Format dateFormat = new SimpleDateFormat(DD_MM_YYYY);
		String date = dateFormat.format(source.getTime());
		Format timeFormat = new SimpleDateFormat(HH_MM_A);
		String time = timeFormat.format(source.getTime());
		TimeDto timeDto = new TimeDto(date: date, time: time);
		return timeDto;
	}
}
