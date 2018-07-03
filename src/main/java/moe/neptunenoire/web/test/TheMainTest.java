package moe.neptunenoire.web.test;

import java.time.LocalTime;

import moe.neptunenoire.web.util.SenKiZeSShou;

public class TheMainTest {

	private String str = "";
	private void print() {
		System.out.println("Hello World" + str);
	}

	public static void main(String[] args) {
		try {
			System.out.println(LocalTime.now().toString());
			SenKiZeSShou senKiZeSShou = SenKiZeSShou.loadClass(TheMainTest.class.newInstance());
			senKiZeSShou.RunMethod("print");
			System.out.println(LocalTime.now().toString());
			TheMainTest mainTest = new TheMainTest();
			mainTest.print();
			System.out.println(LocalTime.now().toString());
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
