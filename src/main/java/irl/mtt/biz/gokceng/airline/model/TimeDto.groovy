package irl.mtt.biz.gokceng.airline.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 03.11.2015
 */
@ToString(includeNames = true)
@EqualsAndHashCode(excludes = [])
class TimeDto {
	String date;
	String time;
}