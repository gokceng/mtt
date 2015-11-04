package irl.mtt.biz.gokceng.airline.model
/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
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
