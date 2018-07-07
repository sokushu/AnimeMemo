package moe.neptunenoire.web.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SenKiZeSShou {

	private Class<? extends Object> CVlass;

	private Object obj;

	private SenKiZeSShou(Class<? extends Object> class1, Object obj2) {
		this.CVlass = class1;
		obj = obj2;
	}

	public static SenKiZeSShou loadClass(Object obj) {
		return new SenKiZeSShou(obj.getClass(), obj);
	}

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

	public void setField(String fieldName, Object value) {
		try {
			Field field = getFieldObject(fieldName);
			field.setAccessible(true);
			field.set(obj, value);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

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

	public void clearFieldValue(String fieldName) throws Exception {
		List<String> list = new ArrayList<String>() {{
			add(Map.class.getName());
			add(List.class.getName());
			add(String.class.getName());
		}};
		Field field = getFieldObject(fieldName);
		String fieldClassName = field.getType().getName();
		if (list.contains(fieldClassName)) {

		}
	}

	public Method getMethod(String methodName) throws Exception {
		for (Method var : CVlass.getDeclaredMethods()) {
			var.setAccessible(true);
			if (methodName.equals(var.getName())) {
				return var;
			}
		}
		throw new Exception(methodName + " Not Found");
	}

	@Deprecated
	public Object RunMethod(Method method, Object...objects) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return method.invoke(obj, objects);
	}

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