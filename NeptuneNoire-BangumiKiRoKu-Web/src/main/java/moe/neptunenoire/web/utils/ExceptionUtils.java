package moe.neptunenoire.web.utils;

import java.io.File;
import java.io.FileNotFoundException;

public class ExceptionUtils {

	/**
	 *
	 * @param obj
	 */
	public static void throwIfNull(Object obj) {
		if (obj == null)
			throw new NullPointerException();
	}

	/**
	 *
	 * @param obj
	 * @param message
	 */
	public static void throwIfNull(Object obj, String message) {
		if (obj == null)
			throw new NullPointerException(message);
	}

	/**
	 *
	 * @param file
	 * @throws FileNotFoundException
	 */
	public static void throwIfFileNotFound(File file) throws FileNotFoundException {
		if (!file.exists())
			throw new FileNotFoundException();
	}

	/**
	 *
	 * @param file
	 * @throws FileNotFoundException
	 */
	public static void throwIfFileNotFound(File file, String message) throws FileNotFoundException {
		if (!file.exists())
			throw new FileNotFoundException(message);
	}

}
