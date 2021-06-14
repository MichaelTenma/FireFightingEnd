package cn.ltiex.firefighting.end.TypeAdapter.Gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.postgis.Geometry;

import java.io.IOException;

public class PostGISGeometryTypeAdapter extends TypeAdapter<Geometry> {
    @Override
    public void write(JsonWriter jsonWriter, Geometry geometry) throws IOException {
        StringBuffer sb = new StringBuffer("");
        if(geometry != null){
            geometry.outerWKT(sb, false);
        }
        jsonWriter.value(sb.toString());
    }

    @Override
    public Geometry read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
