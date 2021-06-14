//package cn.ltiex.firefighting.end.TypeAdapter.Gson;
//
//import com.google.gson.TypeAdapter;
//import com.google.gson.stream.JsonReader;
//import com.google.gson.stream.JsonToken;
//import com.google.gson.stream.JsonWriter;
//import com.mapbox.services.commons.geojson.FeatureCollection;
//import com.mapbox.services.commons.geojson.GeoJSON;
//import com.mapbox.services.commons.geojson.Geometry;
//import com.mapbox.services.commons.geojson.GeometryCollection;
//
//import java.io.IOException;
//
//public class GeoEleAdapter extends TypeAdapter<GeoJSON> {
//    @Override
//    public void write(JsonWriter jsonWriter, GeoJSON geoJSON) throws IOException {
//        if(geoJSON == null){
//            jsonWriter.value("");
//            return;
//        }
//        jsonWriter.jsonValue(geoJSON.toJson());
//    }
//
//    @Override
//    public GeoJSON read(JsonReader jsonReader) throws IOException {
//        if(jsonReader.peek() == JsonToken.STRING){
//            return GeometryCollection.fromJson(jsonReader.nextString());
//        }
//        return null;
//    }
//}
