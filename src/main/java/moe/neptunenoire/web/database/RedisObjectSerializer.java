package moe.neptunenoire.web.database;

public class RedisObjectSerializer /*implements RedisSerializer<Object>*/ {
//    private Converter<Object, byte[]> serializer = new SerializingConverter();
//    private Converter<byte[], Object> deserializer = new DeserializingConverter();
//    static final byte[] EMPTY_ARRAY = new byte[0];
//
//    @Override
//    public Object deserialize(byte[] bytes) {
//        if (isEmpty(bytes)) {
//            return null;
//        }
//        try {
//            return deserializer.convert(bytes);
//        } catch (Exception ex) {
//            throw new SerializationException("Cannot deserialize", ex);
//        }
//    }
//
//    @Override
//    public byte[] serialize(Object object) {
//        if (object == null) {
//            return EMPTY_ARRAY;
//        }
//        try {
//            return serializer.convert(object);
//        } catch (Exception ex) {
//            return EMPTY_ARRAY;
//        }
//    }
//
//    private boolean isEmpty(byte[] data) {
//        return (data == null || data.length == 0);
//    }
}
