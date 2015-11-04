package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.converter.ConversionServiceAwareConverter
import irl.mtt.biz.gokceng.airline.model.PriceDto
import org.springframework.util.Assert
import org.springframework.util.StringUtils

import javax.annotation.concurrent.ThreadSafe

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
@ThreadSafe
class StringToPriceDtoConverter extends ConversionServiceAwareConverter<String, PriceDto> {
	private final String delimiter

	StringToPriceDtoConverter(String delimiter) {
		Assert.hasLength(delimiter)
		this.delimiter = delimiter
	}

	@Override
	PriceDto convert(String source) {
		Assert.hasText(source)

		String[] splittedSource = StringUtils.split(source, delimiter);
		Assert.isTrue(splittedSource != null && splittedSource.length == 2)

		String currency = splittedSource[0];
		BigDecimal amount = new BigDecimal(splittedSource[1]);
		return new PriceDto(currency: currency, amount: amount);
	}
}