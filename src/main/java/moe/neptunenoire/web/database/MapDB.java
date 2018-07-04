package moe.neptunenoire.web.database;

import java.io.IOException;
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
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public Map<String, Object> deserialize(DataInput2 input, int available) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
