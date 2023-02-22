package de.flp.easyfiles.mapper.mappings.java_index.primitiv;

import de.flp.easyfiles.mapper.ObjectMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;

@MapperInfo(clazz = float.class)
public class FloatMapper implements TypeMapper {
    @Override
    public void unMap(ObjectMapper clazz, Field field, JSONObject jsObject) {
        try {
            field.set(clazz, jsObject.getFloat(field.getName()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
