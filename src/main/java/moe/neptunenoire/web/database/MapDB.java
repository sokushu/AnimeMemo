package moe.neptunenoire.web.database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;
import org.mapdb.serializer.GroupSerializer;
import org.mapdb.serializer.GroupSerializerObjectArray;

/**
 *
 * @author M
 *
 * @param <A>
 */
public interface MapDB<A> extends Serializer<A> {

	/**
	 *
	 */
	GroupSerializer<Map<String, Object>> MAP = new MapDBMap();

}

/**
 *
 * @author M
 *
 */
class ObjectByteArrayUtil{

	public static byte[] ObjectToByteArrays(Object object) throws IOException{
		byte[] bb = null;
		try (ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
				ObjectOutputStream in = new ObjectOutputStream(arrayOutputStream);){
			in.writeObject(object);
			in.flush();
			bb = arrayOutputStream.toByteArray();
		}
		return bb;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> ByteArraysToMap_String_Object_(byte[] bs) throws IOException {
		return (Map<String, Object>) ByteArraysToObject(bs);
	}

	public static Object ByteArraysToObject(byte[] bs) throws IOException{
		try(ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bs);
				ObjectInputStream in = new ObjectInputStream(arrayInputStream)){
				return in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}

/**
 *
 * @author M
 *
 */
class MapDBMap extends GroupSerializerObjectArray<Map<String, Object>> {
    @Override
    public boolean isTrusted() {
        return true;
    }

	@Override
	public void serialize(DataOutput2 out, Map<String, Object> value) throws IOException {
		byte[] bb = ObjectByteArrayUtil.ObjectToByteArrays(value);
		out.packInt(bb.length);
		out.write(bb);
	}

	@Override
	public Map<String, Object> deserialize(DataInput2 input, int available) throws IOException {
		int size = input.unpackInt();
		byte[] ret = new byte[size];
		input.readFully(ret);
		return ObjectByteArrayUtil.ByteArraysToMap_String_Object_(ret);
	}

}
