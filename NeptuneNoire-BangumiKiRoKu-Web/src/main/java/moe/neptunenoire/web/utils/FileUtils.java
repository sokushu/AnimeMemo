package moe.neptunenoire.web.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

	/**
	 *
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String readToString(File file) throws FileNotFoundException, IOException {
		ExceptionUtils.throwIfNull(file, "file is null");
		ExceptionUtils.throwIfFileNotFound(file, "file not found " + file.getPath());
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.defaultCharset()))){
			return bufferedReader.lines().collect(Collectors.joining());
		}
	}

	/**
	 *
	 * @param file
	 * @param Charset
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String readToString(File file, String Charset) throws FileNotFoundException, IOException {
		ExceptionUtils.throwIfNull(file, "file is null");
		ExceptionUtils.throwIfFileNotFound(file, "file not found " + file.getPath());
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset))){
			return bufferedReader.lines().collect(Collectors.joining());
		}
	}

	/**
	 *
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<String> readToList(File file) throws FileNotFoundException, IOException{
		ExceptionUtils.throwIfNull(file, "file is null");
		ExceptionUtils.throwIfFileNotFound(file, "file not found " + file.getPath());
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.defaultCharset()))){
			return bufferedReader.lines().collect(Collectors.toList());
		}
	}

	/**
	 *
	 * @param file
	 * @param Charset
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<String> readToList(File file, String Charset) throws FileNotFoundException, IOException{
		ExceptionUtils.throwIfNull(file, "file is null");
		ExceptionUtils.throwIfFileNotFound(file, "file not found " + file.getPath());
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset))){
			return bufferedReader.lines().collect(Collectors.toList());
		}
	}

	/**
	 *
	 * @param file
	 * @param str
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeByString(File file, String str) throws FileNotFoundException, IOException {
		ExceptionUtils.throwIfNull(file, "file is null");
		makeFilesPath(file);
		try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.defaultCharset()))){
			bufferedWriter.write(StringUtils.defaultString(str));
		}
	}

	/**
	 *
	 * @param file
	 * @param str
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeByList(File file, List<String> str) throws FileNotFoundException, IOException {
		ExceptionUtils.throwIfNull(file);
		makeFilesPath(file);
		try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.defaultCharset()))) {
			bufferedWriter.write(String.join(System.lineSeparator(), str));
		}
	}

	/**
	 *
	 * @param file
	 * @param str
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeByArray(File file, String[] str) throws FileNotFoundException, IOException {
		writeByList(file, ArrayUtils.ArrayToList(str));
	}

	/**
	 *
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static boolean makeFilesPath(String filePath) throws IOException {
		File file = new File(filePath);
		if (file.exists()) return true;

		File mk = new File(StringUtils.chomp(filePath, file.getName()));
		if (mk.mkdirs()) {
			return file.createNewFile();
		}
		return false;
	}

	/**
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static boolean makeFilesPath(File file) throws IOException {
		if (file.exists()) return true;

		File mk = new File(StringUtils.chomp(file.getPath(), file.getName()));
		if (mk.mkdirs()) {
			return file.createNewFile();
		}
		return false;
	}

}
