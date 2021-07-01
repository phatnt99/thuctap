package net.freetuts.backend.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

@Configuration
public class JacksonConfiguration {

	@Bean
	public ObjectMapper objectMapper(JavaTimeModule javaTimeModule,
			MyBeanSerializerModifier myBeanSerializerModifier) {

		ObjectMapper mapper = new ObjectMapper();

		mapper.setSerializerFactory(mapper.getSerializerFactory()
				.withSerializerModifier(myBeanSerializerModifier));

		mapper.registerModule(javaTimeModule);

		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
						false);

		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		mapper.setFilterProvider(
				new SimpleFilterProvider().setFailOnUnknownId(false));

		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		return mapper;
	}

	@Bean
	public JavaTimeModule javaTimeModule() {
		String localTimePattern     = "HH:mm:ss";
		String localDatePattern     = "dd-MM-yyyy";
		String localDateTimePattern = "dd-MM-yyyy HH:mm:ss";

		JavaTimeModule javaTimeModule = new JavaTimeModule();

		javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(
				DateTimeFormatter.ofPattern(localTimePattern)));
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(
				DateTimeFormatter.ofPattern(localDatePattern)));
		javaTimeModule.addSerializer(LocalDateTime.class,
				new LocalDateTimeSerializer(
						DateTimeFormatter.ofPattern(localDateTimePattern)));

		javaTimeModule.addDeserializer(LocalTime.class,
				new LocalTimeDeserializer(
						DateTimeFormatter.ofPattern(localTimePattern)));

		javaTimeModule.addDeserializer(LocalDate.class,
				new LocalDateDeserializer(
						DateTimeFormatter.ofPattern(localDatePattern)));
		javaTimeModule.addDeserializer(LocalDateTime.class,
				new LocalDateTimeDeserializer(
						DateTimeFormatter.ofPattern(localDateTimePattern)));

		return javaTimeModule;
	}

	@Bean
	public MyBeanSerializerModifier myBeanSerializerModifier() {
		return new MyBeanSerializerModifier();
	}

}