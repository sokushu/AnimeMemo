package moe.neptunenoire.web.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class MoeArrayList<E> extends ArrayList<E> {

	private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

	transient Object[] elementData;

	private int size;

	private static final int DEFAULT_CAPACITY = 10;

	public MoeArrayList() {
		this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		try {
			return (E) elementData[index];
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean add(E e) {
		ensureCapacityInternal(size + 1);
		elementData[size++] = e;
		return true;
	}

	private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
}
