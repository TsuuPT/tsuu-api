package net.sandrohc.tsuu.api.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import net.sandrohc.tsuu.api.mongodb.converters.OffsetDateTimeConverter;

@Configuration
public class MongoConvertersConfig {

	@Bean
	public MongoCustomConversions mongoCustomConversions() {
		return new MongoCustomConversions(Arrays.asList(
				new OffsetDateTimeConverter.Read(),
				new OffsetDateTimeConverter.Write()
		));
	}

}
