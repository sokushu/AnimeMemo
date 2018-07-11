package moe.neptunenoire.web.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author M
 *
 */
public class SenKiZeSShou {

	/**
	 *
	 */
	private Class<? extends Object> CVlass;

	/**
	 *
	 */
	private Object obj;

	/**
	 *
	 * @param class1
	 * @param obj2
	 */
	private SenKiZeSShou(Class<? extends Object> class1, Object obj2) {
		this.CVlass = class1;
		obj = obj2;
	}

	/**
	 *
	 * @param obj
	 * @return
	 */
	public static SenKiZeSShou loadClass(Object obj) {
		return new SenKiZeSShou(obj.getClass(), obj);
	}

	/**
	 *
	 * @param fieldsName
	 * @return
	 */
	public Map<String, Object> getFields(String...fieldsName) {
		Map<String, Object> data = new HashMap<>();
		List<String> fieldName = Arrays.asList(fieldsName);
		for (Field field : CVlass.getDeclaredFields()) {
			field.setAccessible(true);
			String name = field.getName();
			if (fieldName.contains(name)) {
				try {
					data.put(name, field.get(obj));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}

	/**
	 *
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public Object getField(String fieldName) throws Exception {
		Class<?> theclass = CVlass;
		while (theclass != null) {
			for (Field field : theclass.getDeclaredFields()) {
				field.setAccessible(true);
				String name = field.getName();
				if (fieldName.equals(name)) {
					try {
						return field.get(obj);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						throw e;
					}
				}
			}
			theclass = theclass.getSuperclass();
		}
		throw new Exception(fieldName+" NOT Found");
	}

	/**
	 *
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException
	 */
	private Field getFieldObject(String fieldName) throws NoSuchFieldException {
		Class<?> theclass = CVlass;
		while (theclass != null) {
			for (Field field : theclass.getDeclaredFields()) {
				field.setAccessible(true);
				String name = field.getName();
				if (fieldName.equals(name)) {
					return field;
				}
			}
			theclass = theclass.getSuperclass();
		}
		throw new NoSuchFieldException(fieldName+" NOT Found");
	}

	/**
	 *
	 * @param fieldName
	 * @param value
	 */
	public void setField(String fieldName, Object value) {
		try {
			Field field = getFieldObject(fieldName);
			field.setAccessible(true);
			field.set(obj, value);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 */
	public void printAllFieldName() {
		List<Field> fieldList = new ArrayList<>();
		Class<?> theclass = CVlass;
		while (theclass != null) {
			fieldList.addAll(Arrays.asList(theclass.getDeclaredFields()));
			theclass = theclass.getSuperclass();
		}
		for (Field field : fieldList) {
			System.out.println(field.getName()+" : Type = "+field.getType().getSimpleName());
		}
	}

	/**
	 *
	 */
	public void printAllMethodName() {
		List<Method> fieldList = new ArrayList<>();
		Class<?> theclass = CVlass;
		while (theclass != null) {
			fieldList.addAll(Arrays.asList(theclass.getDeclaredMethods()));
			theclass = theclass.getSuperclass();
		}
		for (Method field : fieldList) {
			System.out.println(field.getName()+" : Type = "+field.getReturnType().getSimpleName());
		}
	}

	/**
	 *
	 * @param methodNames
	 * @return
	 */
	public Map<String, Method> getMethods(String...methodNames) {
		List<String> list = Arrays.asList(methodNames);
		Map<String, Method> data = new HashMap<>();
		for (Method var : CVlass.getDeclaredMethods()) {
			var.setAccessible(true);
			if (methodNames.length > 0) {
				if (list.contains(var.getName())) {
					data.put(var.getName(), var);
				}
			}else {
				data.put(var.getName(), var);
			}
		}
		return data;
	}

	/**
	 *
	 * @param fieldName
	 * @throws Exception
	 */
	public void clearFieldValue(String fieldName) throws Exception {
		Field field = getFieldObject(fieldName);
		String fieldClassName = field.getType().getName();
		if (fieldClassName.equals(Map.class.getName())) {
			field.set(obj, new HashMap<>());
		}else if (fieldClassName.equals(List.class.getName())) {
			field.set(obj, new ArrayList<>());
		}else if (fieldClassName.equals(String.class.getName())) {
			field.set(obj, "");
		}
	}

	/**
	 *
	 * @param methodName
	 * @return
	 * @throws Exception
	 */
	public Method getMethod(String methodName) throws Exception {
		for (Method var : CVlass.getDeclaredMethods()) {
			var.setAccessible(true);
			if (methodName.equals(var.getName())) {
				return var;
			}
		}
		throw new Exception(methodName + " Not Found");
	}

	/**
	 *
	 * @param method
	 * @param objects
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@Deprecated
	public Object RunMethod(Method method, Object...objects) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return method.invoke(obj, objects);
	}

	/**
	 *
	 * @param methodName
	 * @param objects
	 * @return
	 * @throws Exception
	 */
	public Object RunMethod(String methodName, Object...objects) throws Exception{
		try {
			Method method = getMethod(methodName);
			return method.invoke(obj, objects);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

}