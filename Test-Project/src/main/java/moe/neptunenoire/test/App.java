package moe.neptunenoire.test;

import moe.neptunenoire.test.classtest.GetClassTest;
import moe.neptunenoire.test.classtest.Type;

/**
 * Hello world!
 *
 */
public class App {

	private String abcd = "Hello World";

	public static void main(String[] args) {
		GetClassTest classTest = GetClassTest.loadClass(new App());
		try {
			classTest.setField("abcd", new Type<String>("test"));
			System.out.println(classTest.getField("abcd", new Type<String>()));
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}

