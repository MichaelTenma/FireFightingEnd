package cn.ltiex.firefighting.end.Configuration;

import cn.ltiex.firefighting.end.TypeAdapter.Gson.EnumAdapterFactory;
import cn.ltiex.firefighting.end.TypeAdapter.Gson.PostGISGeometryTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.postgis.Geometry;
import org.postgis.GeometryCollection;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import java.util.*;

@Configuration
public class GsonConfig {
    @Bean
    public Gson gson() {
        GsonBuilder builder = new GsonBuilder();
        builder
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeHierarchyAdapter(Geometry.class, new PostGISGeometryTypeAdapter())
                .registerTypeAdapterFactory(new EnumAdapterFactory())
        ;
        return builder.create();
    }

    @Bean
    public HttpMessageConverters customConverters() {

        Collection<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setGson(gson());
        messageConverters.add(gsonHttpMessageConverter);

        return new HttpMessageConverters(true, messageConverters);
    }
}