package moe.neptunenoire.web.test;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class TheMainTest {

	private String str = "";
	private void print() {
		System.out.println("Hello World" + str);
	}

	public static class Hello{
		public void go() {

		}
	}

	public static void main(String[] args) {
		try {
			TheMainTest mainTest = new TheMainTest();

			ClassPool pool = ClassPool.getDefault();
			pool.insertClassPath(new ClassClassPath(mainTest.getClass()));

			CtClass ctClass = pool.get("moe.neptunenoire.web.test.Test");

			CtMethod ctMethod = ctClass.getDeclaredMethod("sayHello");

			ctMethod.setBody("System.out.println(\"this method is changed dynamically!\");");

			ctClass.toClass();

			Test test = new Test();
			test.sayHello();

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
			ClassLoader classPool = ClassPool.getDefault().getClassLoader();

			Thread.currentThread().setContextClassLoader(classPool);

			Class<?> theclass = classPool.loadClass(this.getClass().getName());

			CtClass pool = ClassPool.getDefault().get("moe.neptunenoire.web.test.TheMainTest");

		    CtMethod ctMethod = pool.getDeclaredMethod("test");
		    ctMethod.setBody("System.out.println(\"this method is changed dynamically!\");");
		    pool.toClass();
		    System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void test() {
		System.out.println("TE");

	}

}
