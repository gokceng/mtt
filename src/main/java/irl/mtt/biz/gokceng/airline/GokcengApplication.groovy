package irl.mtt.biz.gokceng.airline

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ImportResource
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
@ImportResource(value = 'classpath*:META-INF/spring/**/*.xml')
@PropertySource(value = "classpath:application.properties")
class GokcengApplication {
	static void main(String[] args) {
		SpringApplication.run GokcengApplication, args
	}
}
