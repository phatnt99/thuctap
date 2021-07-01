package net.freetuts.backend.utils;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * Thấy cái Jackson xịn xò hơn nên mình dùng Jackson luôn cho nó lành.
 * Viết sẵn rồi, mỗi đứa một inner class để viết mắý cái value cho Jackson
 * annotation, không liên quan đến bố con thằng nào.
 * 
 * @author MaiDat
 */
public class JacksonUtil {

	/**
	 * Jackson properties for User class
	 * 
	 * @author DatMV1
	 */
	public static class User {
		/**
		 * Property value for annotation @JsonFilter
		 */
		public static final String JSON_FILLTER = "userFilter";

		public static final String ID_JSON_PROPERTY = "id";

		public static final String USERNAME_JSON_PROPERTY = "username";

		public static final String PASSWORD_JSON_PROPERTY = "password";

		public static final String DATE_JSON_PROPERTY = "date";

	}

	/**
	 * Jackson properties for Coupon class
	 * 
	 * @author DatMV1
	 */
	public static class Coupon {
		/**
		 * Property value for annotation @JsonFilter
		 */
		public static final String JSON_FILLTER = "";
	}

	/**
	 * Jackson properties for User class
	 * 
	 * @author PhatNT15
	 */
	public static class Category {
		/**
		 * Property value for annotation @JsonFilter
		 */
		public static final String JSON_FILLTER = "";
	}

	/**
	 * Jackson properties for Post class
	 * 
	 * @author PhatNT15
	 */
	public static class Post {
		/**
		 * Property value for annotation @JsonFilter
		 */
		public static final String JSON_FILLTER = "userFilter";
	}

	/**
	 * Jackson properties for Course class
	 * 
	 * @author ThuyNTT14
	 */
	public static class Course {
		/**
		 * Property value for annotation @JsonFilter
		 */
		public static final String JSON_FILLTER = "";
	}

	/**
	 * Filtering out specified properties in <code>target</code> object class
	 * based on <code>expectJsonProperties</code> parameter
	 * 
	 * @param target
	 *     Object to be filter out specified properties
	 * @param fillterId
	 *     Id of @JsonFilter annotation in <code>target</code> object class.
	 * @param expectJsonProperties
	 * 
	 * @return
	 * 
	 * @author DatMV1
	 */
	public static MappingJacksonValue getExpectJacksonValue(Object target,
			String fillterId, String... expectJsonProperties) {

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(
				target);

		FilterProvider filters = new SimpleFilterProvider()
				.addFilter(fillterId,
						SimpleBeanPropertyFilter
								.filterOutAllExcept(expectJsonProperties));

		mappingJacksonValue.setFilters(filters);

		return mappingJacksonValue;
	}

}
