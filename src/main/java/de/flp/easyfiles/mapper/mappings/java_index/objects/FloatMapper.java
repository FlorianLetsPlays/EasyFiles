package de.flp.easyfiles.mapper.mappings.java_index.objects;

import de.flp.easyfiles.mapper.ObjectMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;

@MapperInfo(clazz = Float.class)
public class FloatMapper implements TypeMapper {
    @Override
    public void unMap(ObjectMapper clazz, Field field, JSONObject jsObject) {
        try {
            field.set(clazz, Float.parseFloat(jsObject.get(field.getName()).toString()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
