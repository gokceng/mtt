package irl.mtt.biz.gokceng.airline.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 04.11.2015
 */
class AvailabilityDto {
	@JsonProperty("availability")
	Set<FlightDtoWrapper> flights;
}
