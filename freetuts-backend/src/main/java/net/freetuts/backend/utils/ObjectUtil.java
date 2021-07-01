package net.freetuts.backend.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * Cái class này mới đầu t làm theo kiểu anh dương là tạo ra một cái json object
 * *
 * Ròi mapping từ cái Entity ra Json rồi trả về Client
 * Mà lúc sao t lướt web một hồi thấy cái Jackson gì gì đó xịn xò hơn.
 * Nên cái class này để đó vậy
 * Mốt có dùnng thì lấy ra không thì xóa cmnr cho gọn.
 * 
 * @author MaiDat
 */
public class ObjectUtil {



	private static final Logger LOGGER = LoggerFactory
			.getLogger(ObjectUtil.class);
	/**
	 * Cái này dùng để mapper từ List ra List
	 */
	public static <S, T> List<T> copyPropertiesList(List<S> source,
			Class<T> targetclazz) {

		List<T> a = new ArrayList<>();
		try {
			for (S s : source) {
				T t = targetclazz.getDeclaredConstructor().newInstance();
				BeanUtils.copyProperties(s, t);
				a.add(t);
			}
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {

			String errorMSg = "Can not copy properties from "
					+ source.getClass() + " to " + targetclazz;
			LOGGER.error(errorMSg, e);

		}
		return a;

	}

	/**
	 * Cái này dùng để mapper từ Object ra Object
	 */
	public static <T, S> T copyPropertiesObject(S source,
			Class<T> targetclazz) {
		T t = null;
		try {
			t = targetclazz.getDeclaredConstructor().newInstance();
			BeanUtils.copyProperties(source, t);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {

			String errorMSg = "Can not copy properties from "
					+ source.getClass() + " to " + targetclazz;
			LOGGER.error(errorMSg, e);

		}
		return t;

	}
	
	public static void copyNonNullProperties(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}
	public static String[] getNullPropertyNames(Object source) {
		List<String> nullValueFields = new ArrayList<>();

		try {
			List<Field> field = getAllFieldsList(source.getClass());
			for (Field f : field) {
				f.setAccessible(true);
				if (f.get(source) == null) {
					nullValueFields.add(f.getName());
				}
			}
		} catch (Exception e) {
			LOGGER.error("ERROR", e);
		}

		return nullValueFields.toArray(new String[0]);
	}
	
	public static List<Field> getAllFieldsList(final Class<?> clazz) {
		final List<Field> allFieldsIncludeSuperClass = new ArrayList<>();
		Class<?>          currentClass               = clazz;
		while (currentClass != null) {
			final Field[] declaredFields = currentClass.getDeclaredFields();
			Collections.addAll(allFieldsIncludeSuperClass, declaredFields);
			currentClass = currentClass.getSuperclass();
		}
		return allFieldsIncludeSuperClass;
	}
}
