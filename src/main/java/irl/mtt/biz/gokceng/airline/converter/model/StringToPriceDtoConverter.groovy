package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.converter.ConversionServiceAwareConverter
import irl.mtt.biz.gokceng.airline.model.PriceDto
import org.springframework.util.StringUtils

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
class StringToPriceDtoConverter extends ConversionServiceAwareConverter<String, PriceDto> {
	private static final String WHITE_SPACE = " "

	@Override
	PriceDto convert(String source) {
		PriceDto priceDto = new PriceDto();
		String[] splittedSource = StringUtils.split(source, WHITE_SPACE);
		priceDto.currency = splittedSource[0];
		priceDto.amount = new BigDecimal(splittedSource[1]);
		return priceDto;
	}
}
