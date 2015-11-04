package irl.mtt.biz.gokceng.airline.converter.model

import irl.mtt.biz.gokceng.airline.converter.ConversionServiceAwareConverter
import irl.mtt.biz.gokceng.airline.model.FarePriceDto
import irl.mtt.biz.gokceng.airline.model.FarePricesDto
import irl.mtt.biz.gokceng.airline.schema.model.Fare
import irl.mtt.biz.gokceng.airline.schema.model.Fares
import org.springframework.util.Assert
import org.springframework.util.CollectionUtils

import javax.annotation.concurrent.ThreadSafe

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
@ThreadSafe
class FaresToFarePricesDtoConverter extends ConversionServiceAwareConverter<Fares, FarePricesDto> {
	private static final String YIF_CLASS = "YIF"
	private static final String CIF_CLASS = "CIF"
	private static final String FIF_CLASS = "FIF"

	@Override
	FarePricesDto convert(Fares source) {
		Assert.notNull(source);

		FarePriceDto economy = getFarePriceDto(source.fare, YIF_CLASS);
		FarePriceDto business = getFarePriceDto(source.fare, CIF_CLASS);
		FarePriceDto first = getFarePriceDto(source.fare, FIF_CLASS);
		return new FarePricesDto(economy: economy, business: business, first: first);
	}

	FarePriceDto getFarePriceDto(List<Fare> fares, String fareType) {
		Assert.hasText(fareType);
		if (CollectionUtils.isEmpty(fares)) {
			return null;
		}
		Fare fare = fares.find { it -> fareType.equals(it.clazz) };
		if (fare == null) {
			return null;
		}
		return convertToType(fare, FarePriceDto.class);
	}
}
