package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.converter.ConversionServiceAwareConverter
import irl.mtt.biz.gokceng.airline.model.FarePriceDto
import irl.mtt.biz.gokceng.airline.model.PriceDto
import irl.mtt.biz.gokceng.airline.schema.model.Fare
import org.springframework.core.convert.ConversionService

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
class FareToFarePriceDtoConverter extends ConversionServiceAwareConverter<Fare, FarePriceDto> {

	@Override
	FarePriceDto convert(Fare source) {
		FarePriceDto farePriceDto = new FarePriceDto();
		ConversionService conversionService = conversionService();
		farePriceDto.bookingFee = conversionService.convert(source.fees, PriceDto.class);
		farePriceDto.tax = conversionService.convert(source.tax, PriceDto.class);
		farePriceDto.ticket = conversionService.convert(source.basePrice, PriceDto.class);
		return farePriceDto;
	}
}
