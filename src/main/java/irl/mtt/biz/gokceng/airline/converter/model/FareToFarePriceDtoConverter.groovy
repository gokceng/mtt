package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.converter.ConversionServiceAwareConverter
import irl.mtt.biz.gokceng.airline.model.FarePriceDto
import irl.mtt.biz.gokceng.airline.model.PriceDto
import irl.mtt.biz.gokceng.airline.schema.model.Fare

import javax.annotation.concurrent.ThreadSafe

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
@ThreadSafe
class FareToFarePriceDtoConverter extends ConversionServiceAwareConverter<Fare, FarePriceDto> {
	@Override
	FarePriceDto convert(Fare source) {
		PriceDto bookingFee = convertToType(source.fees, PriceDto.class);
		PriceDto tax = convertToType(source.tax, PriceDto.class);
		PriceDto ticket = convertToType(source.basePrice, PriceDto.class);

		return new FarePriceDto(bookingFee: bookingFee, tax: tax, ticket: ticket);
	}
}
