package irl.mtt.biz.gokceng.airline

import irl.mtt.biz.gokceng.airline.schema.model.ObjectFactory
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Shared
import spock.lang.Specification

/**
 * @author Gökçen Güner
 * @since 1.0.0
 * 04.11.2015
 */
@WebAppConfiguration
@SpringApplicationConfiguration(classes = GokcengApplication)
class SpockApplicationTest extends Specification {
	@Shared
	protected ObjectFactory objectFactory = new ObjectFactory();

	def "Dummy"() {
		expect:
		1 == 1
	}
}
