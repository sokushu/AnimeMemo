package moe.neptunenoire.test;

import moe.neptunenoire.test.io.FileTest;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		FileTest fileTest = new FileTest();
		String a = fileTest.readText("E:\\moePachong\\moePachong\\src\\main\\java\\moe\\neptunenoire\\TestRun.java");
		System.out.println(a);
	}
}

