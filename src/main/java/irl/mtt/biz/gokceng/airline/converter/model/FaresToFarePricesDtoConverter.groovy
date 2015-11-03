package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.converter.ConversionServiceAwareConverter
import irl.mtt.biz.gokceng.airline.model.FarePriceDto
import irl.mtt.biz.gokceng.airline.model.FarePricesDto
import irl.mtt.biz.gokceng.airline.schema.model.Fare
import irl.mtt.biz.gokceng.airline.schema.model.Fares
import org.springframework.core.convert.ConversionService

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
class FaresToFarePricesDtoConverter extends ConversionServiceAwareConverter<Fares, FarePricesDto> {
	@Override
	FarePricesDto convert(Fares source) {
		FarePricesDto farePricesDto = new FarePricesDto();
		ConversionService conversionService = conversionService();

		Fare economy = source.fare.find { it -> "YIF".equals(it.clazz) };
		farePricesDto.economy = conversionService.convert(economy, FarePriceDto.class);

		Fare business = source.fare.find { it -> "CIF".equals(it.clazz) };
		farePricesDto.business = conversionService.convert(business, FarePriceDto.class);

		Fare first = source.fare.find { it -> "FIF".equals(it.clazz) };
		farePricesDto.first = conversionService.convert(first, FarePriceDto.class);

		return farePricesDto;
	}
}
