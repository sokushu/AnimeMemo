package xyz.bangumi.core.mydatabase;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * 自己实现的map集合，用来当作临时备用数据库
 */
public class MySQLMap<K, V> extends Hashtable implements Map{

    private static final long serialVersionUID = 1L;

	public MySQLMap(){
        super();
    }

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		return false;
	}

	@Override
	public Object get(Object key) {
		return null;
	}

	@Override
	public Object put(Object key, Object value) {
		return null;
	}

	@Override
	public Object remove(Object key) {
		return null;
	}

	@Override
	public void putAll(Map m) {
		
	}

	@Override
	public void clear() {
		
	}

	@Override
	public Set keySet() {
		return null;
	}

	@Override
	public Collection values() {
		return null;
	}

	@Override
	public Set entrySet() {
		return null;
	}
}