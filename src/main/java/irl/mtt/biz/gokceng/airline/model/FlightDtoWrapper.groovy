package irl.mtt.biz.gokceng.airline.model

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 04.11.2015
 *
 * @see <a href="http://stackoverflow.com/a/2494038/1108890">https://stackoverflow.com/</a>
 */
@EqualsAndHashCode(excludes = [])
class FlightDtoWrapper {
	@JsonProperty(value = "flight")
	FlightDto flight;
}
