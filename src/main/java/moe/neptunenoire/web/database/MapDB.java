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

public interface MapDB<A> extends Serializer<A> {

	GroupSerializer<Map<String, Object>> MAP = new MapDBList();

}

class MapDBList extends GroupSerializerObjectArray<Map<String, Object>> {
    @Override
    public boolean isTrusted() {
        return true;
    }

	@Override
	public void serialize(DataOutput2 out, Map<String, Object> value) throws IOException {
		try (ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
				ObjectOutputStream in = new ObjectOutputStream(arrayOutputStream);){
			in.writeObject(value);
			in.flush();
			byte[] bb = arrayOutputStream.toByteArray();
			out.write(bb);
			System.out.println("******---******"+bb.length);
			for (byte b : bb) {
				System.out.println(b);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> deserialize(DataInput2 input, int available) throws IOException {
//		int len = input.internalByteArray();
		byte[] bs = new byte[160];
		for (int i = 0; i < bs.length; i++) {
			bs[i] = input.readByte();
		}
		System.out.println("***********"+bs.length);
		for (byte b : bs) {
			System.out.println(b);
		}
		try(ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bs);
				ObjectInputStream in = new ObjectInputStream(arrayInputStream)){
			try {
				return (Map<String, Object>)(in.readObject());
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				return null;
			}
		}
	}

}
