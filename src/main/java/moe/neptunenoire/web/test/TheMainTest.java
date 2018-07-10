package moe.neptunenoire.web.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import moe.neptunenoire.web.database.MapDB;

public class TheMainTest {

	private String str = "";
	private void print() {
		System.out.println("Hello World" + str);
	}

	public static class Hello{
		public void go() {

		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {

//			DB db = DBMaker.fileDB(new File("D:\\Test\\mapppp")).checksumHeaderBypass().make();
//
//			BTreeMap<String, Map<String, Object>> map = db.treeMap("map").keySerializer(MapDB.STRING).valueSerializer(MapDB.MAP).createOrOpen();
//
//			map.put("123", new HashMap<String, Object>() {{
//				put("Hello", "hi jk");
//			}});
//
//			if (map.get("123") == null) {
//				System.out.println("null");
//			}else {
//				System.out.println(map.get("123").get("Hello").toString());
//			}
//
//			map.close();

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	private static void println() {
		System.out.println("this is static method");
	}

	public void premain() {
		try {
//			TheMainTest mainTest = new TheMainTest();

			ClassPool pool = ClassPool.getDefault();
			pool.insertClassPath(new ClassClassPath(TheMainTest.class.getClass()));

//			Loader cl = new Loader(pool);

			CtClass ctClass = pool.get("moe.neptunenoire.web.test.Test");

			CtMethod ctMethod = ctClass.getDeclaredMethod("sayHello");

			ctMethod.setBody("System.out.println(\"this method is changed dynamically!\");");

			ctClass.toClass();

			System.out.println(TheMainTest.class.getName());
			Test test = new Test();
			test.sayHello();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void test() {
		System.out.println("TE");

	}

}
