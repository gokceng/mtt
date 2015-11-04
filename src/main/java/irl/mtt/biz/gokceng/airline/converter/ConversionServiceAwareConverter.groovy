package irl.mtt.biz.gokceng.airline.converter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterRegistry

import javax.annotation.PostConstruct

/**
 * Base class of @{code Converter} that need to use {@code ConversionService}.
 * Instances of implementing classes must be spring-managed to inject ConversionService.
 *
 * @author Michal Kreuzman
 * @see <a href="https://stackoverflow.com/a/18249958/1108890">https://stackoverflow.com/</a>
 */
abstract class ConversionServiceAwareConverter<S, T> implements Converter<S, T> {
	@Autowired
	private ConversionService conversionService;

	protected <Source, Destination> Destination convertToType(Source source, Class<Destination> destinationClass) {
		conversionService.convert(source, destinationClass);
	}

	/**
	 * Add this converter to {@code ConverterRegistry}.
	 */
	@PostConstruct
	private void register() {
		if (!(conversionService instanceof ConverterRegistry)) {
			throw new IllegalStateException("Can't register Converter to ConverterRegistry");
		}
		((ConverterRegistry) conversionService).addConverter(this);
	}
}
