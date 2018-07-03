package moe.neptunenoire.web.util;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

public class MoeArrayList<E> extends AbstractList<E>
implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

	private final Object[] data = {};

	private int size;

	public MoeArrayList() {}

	@Override
	public E get(int index) {
		try {
			return getindex(index);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private E getindex(int index) {
		return (E) data[index];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean add(E e) {
		data[0] = e;
		return true;
	}

}
