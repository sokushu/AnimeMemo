package moe.neptunenoire.test.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class FileTest {
	
	
	public String readText(String path) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))){
			return br.lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (Exception e) {
			return "error";
		}
	}
}
