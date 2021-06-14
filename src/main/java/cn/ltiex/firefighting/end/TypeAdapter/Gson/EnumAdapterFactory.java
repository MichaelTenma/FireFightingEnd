package cn.ltiex.firefighting.end.TypeAdapter.Gson;

import cn.ltiex.firefighting.end.Response.ResponseCode;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class EnumAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {
        Class<? super T> rawType = type.getRawType();
        if (rawType == ResponseCode.class) {
            return new MyEnumTypeAdapter<T>();
        }
        return null;
    }

    public class MyEnumTypeAdapter<T> extends TypeAdapter<T> {

        public void write(JsonWriter out, T value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            ResponseCode status = (ResponseCode) value;
            // Here write what you want to the JsonWriter.
            out.beginObject();
            out.name("msg");
            out.value(status.getMsg());
            out.name("code");
            out.value(status.getCode());
            out.endObject();
        }

        @Override
        public T read(JsonReader in) throws IOException {
            // Properly deserialize the input (if you use deserialization)
            return null;
        }
    }
}
