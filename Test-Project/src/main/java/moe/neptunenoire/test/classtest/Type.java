package moe.neptunenoire.test.classtest;

public class Type<T>{

	private T t;

	public Type() {}

	public Type(T t) {
		this.t = t;
	}

	T getValue() {
		return t;
	}
}
