package net.sandrohc.tsuu.api.mongodb.converters;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@Component
public class OffsetDateTimeConverter {

	@ReadingConverter
	public static class Read implements Converter<Date, OffsetDateTime> {
		@Override
		public OffsetDateTime convert(Date date) {
			return date.toInstant().atOffset(ZoneOffset.UTC);
		}
	}

	@WritingConverter
	public static class Write implements Converter<OffsetDateTime, Date> {
		@Override
		public Date convert(OffsetDateTime offsetDateTime) {
			return Date.from(offsetDateTime.toInstant());
		}
	}

}
