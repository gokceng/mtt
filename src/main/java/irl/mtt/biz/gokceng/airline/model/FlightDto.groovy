package irl.mtt.biz.gokceng.airline.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
@ToString(excludes = 'farePrices', includeNames = true)
@EqualsAndHashCode(excludes = [])
class FlightDto {
	String operator;
	String flightNumber;
	String departsFrom;
	String arrivesAt;
	TimeDto departsOn;
	TimeDto arrivesOn;
	String flightTime;
	FarePricesDto farePrices;
}
