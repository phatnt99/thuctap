package net.freetuts.backend.config;

import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;

public class MyBeanSerializerModifier extends BeanSerializerModifier {

	// array type
	private JsonSerializer<Object> nullArrayJsonSerializer = new MyNullArrayJsonSerializer2();

	// string and other types
	private JsonSerializer<Object> nullJsonSerializer = new MyNullJsonSerializer2();

	@Override
	public List<BeanPropertyWriter> changeProperties(SerializationConfig config,
			BeanDescription beanDesc,
			List<BeanPropertyWriter> beanProperties) {

		for (int i = 0; i < beanProperties.size(); i++) {
			BeanPropertyWriter writer = beanProperties.get(i);
			if (isArrayType(writer)) {
				writer.assignNullSerializer(this.nullArrayJsonSerializer);
			} else {
				writer.assignNullSerializer(this.nullJsonSerializer);
			}
		}
		return beanProperties;
	}
 

	protected boolean isArrayType(BeanPropertyWriter writer) {
		Class<?> clazz = writer.getPropertyType();
		return clazz.isArray() || clazz.equals(List.class)
				|| clazz.equals(Set.class);

	}

	class MyNullArrayJsonSerializer2 extends JsonSerializer<Object> {

		@Override
		public void serialize(Object value, JsonGenerator gen,
				SerializerProvider serializers) throws IOException {
			if (value == null) {
				gen.writeStartArray();
				gen.writeEndArray();
			}
		}
	}

	class MyNullJsonSerializer2 extends JsonSerializer<Object> {

		@Override
		public void serialize(Object value, JsonGenerator gen,
				SerializerProvider serializers) throws IOException {
			gen.writeString("");
		}

	}

}
