package irl.mtt.biz.gokceng.airline.model

import groovy.transform.EqualsAndHashCode

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
@EqualsAndHashCode(excludes = [])
class FarePricesDto {
	FarePriceDto first;
	FarePriceDto business;
	FarePriceDto economy;
}
