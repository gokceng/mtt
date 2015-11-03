package irl.mtt.biz.gokceng.airline.converter.generic

import irl.mtt.biz.gokceng.airline.converter.CustomConverter
import org.springframework.core.convert.converter.Converter

import java.text.Format
import java.text.SimpleDateFormat

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
class DateToStringConverter implements Converter<Date, String>, CustomConverter {
	public String convert(Date source) {
		Format sdf = new SimpleDateFormat(YYYY_MMDD);
		return sdf.format(source);
	}
}
