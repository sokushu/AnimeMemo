package moe.neptunenoire.web.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import moe.neptunenoire.web.utils.SenKiZeSShou.Type;

/**
 *
 * @author M
 *
 */
public class SenKiZeSShou implements Narcissu{

	/**
	 *
	 * @author M
	 *
	 * @param <T>
	 */
	public static class Type<T>{

		private T value;
		/**
		 *
		 * @param t
		 */
		public Type(T t) {
			value = t;
		}

		/**
		 *
		 */
		public Type() {}

		/**
		 *
		 * @return
		 */
		public T getValue() {
			return value;
		}

	}

	/**
	 *
	 */
	private Class<?> ClassObj;

	/**
	 *
	 */
	private Object obj;

	private Map<String, Field> field;

	private Map<String, Method> method;

	private Map<String, Object> fieldValue;

	/**
	 *
	 * @param ClassObj
	 * @param obj
	 */
	private SenKiZeSShou(Class<?> ClassObj, Object obj) {
		this.ClassObj = ClassObj;
		this.obj = obj;
	}

	/**
	 *
	 * @param classObj
	 * @return
	 */
	public static SenKiZeSShou LoadClass(Object classObj) {
		return new SenKiZeSShou(classObj.getClass(), classObj);
	}

	/**
	 *
	 */
	@Override
	public <T> T getField(String fieldName, Type<T> type) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, Object> getFields() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Map<String, Object> getFields(String... fieldNames) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public <T> void setField(String fieldName, Object value, Type<T> type) {
		Field field = this.field.get(fieldName);

	}

	@Override
	public <T> T RunMethod(String methodName, Type<T> type, Object... Parameters) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	/**
	 *
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private void LoadFields() throws IllegalArgumentException, IllegalAccessException {
		Class<?> theclass = ClassObj;
		Map<String, Field> field = new HashMap<String, Field>() {
			@Override
			public Field put(String key, Field value) {
				if (!this.containsKey(key)) {
					return super.put(key, value);
				}else {
					return null;
				}
			}
		};
		Map<String, Object> fieldValue = new HashMap<String, Object>(){
			@Override
			public Object put(String key, Object value) {
				if (!this.containsKey(key)) {
					return super.put(key, value);
				}else {
					return null;
				}
			}
		};
		while (theclass != null) {
			for (Field field1 : theclass.getDeclaredFields()) {
				field1.setAccessible(true);
				String fieldName = field1.getName();
				field.put(fieldName, field1);
				fieldValue.put(fieldName, field1.get(obj));
			}
			theclass = theclass.getSuperclass();
		}
		this.field = new HashMap<>(field);
		this.fieldValue = new HashMap<>(fieldValue);
	}

	/**
	 *
	 */
	private void LoadMethods() {
		Class<?> theclass = ClassObj;
		Map<String, Method> methods = new HashMap<String, Method>() {
			@Override
			public Method put(String key, Method value) {
				if (!this.containsKey(key)) {
					return super.put(key, value);
				}else {
					return null;
				}
			}
		};
		while (theclass != null) {
			for (Method method : theclass.getDeclaredMethods()) {
				String methodName = method.getName();
				this.method.put(methodName, method);
			}
			theclass = theclass.getSuperclass();
		}
		this.method = new HashMap<>(methods);
	}

	/**
	 *
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private void ReLoad() throws IllegalArgumentException, IllegalAccessException {
		LoadMethods();
		LoadFields();
	}

	/**
	 *
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private void ChackReLoad() throws IllegalArgumentException, IllegalAccessException {
		if (field == null || fieldValue == null) {
			LoadFields();
		}
		if (method == null) {
			LoadMethods();
		}
	}


}

/**
 *
 * @author M
 *
 */
interface Narcissu{

	/**
	 *
	 * @param fieldName
	 * @param type
	 * @return
	 */
	<T> T getField(String fieldName, Type<T> type);

	/**
	 *
	 * @return
	 */
	Map<String, Object> getFields();

	/**
	 *
	 * @param fieldNames
	 * @return
	 */
	Map<String, Object> getFields(String...fieldNames);

	/**
	 *
	 * @param fieldName
	 * @param value
	 * @param type
	 */
	<T> void setField(String fieldName, Object value, Type<T> type);

	/**
	 *
	 * @param methodName
	 * @param type
	 * @param Parameters
	 * @return
	 */
	<T> T RunMethod(String methodName, Type<T> type, Object...Parameters);
}