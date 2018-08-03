package moe.neptunenoire.web.utils;

import java.util.Arrays;
import java.util.List;

public class ArrayUtils {
	/**
	 *
	 * @param len
	 * @return
	 */
	public static String[] makeEmptyStringArray(int len) {
		String[] val = new String[len];
		for (int i = 0; i < val.length; i++) {
			val[i] = StringUtils.EMPTY;
		}
		return val;
	}

	/**
	 *
	 * @param len
	 * @param list
	 * @return
	 */
	public static String[] makeStringArray(int len, List<String> list) {
		int size = list.size();
		int a = size - len;
		if (a < 0) {
			a = Math.abs(a);
			for (int i = 0; i < a; i++) {
				list.add(StringUtils.EMPTY);
			}
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 *
	 * @param array
	 * @return
	 */
	public static <T> List<T> ArrayToList(T[] array){
		return Arrays.asList(array);
	}

}
