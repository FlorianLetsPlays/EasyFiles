package de.flp.easyfiles.mapper.mappings.java_index.objects.arrays;

import de.flp.easyfiles.mapper.ObjektMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;

@MapperInfo(clazz = String[].class)
public class StringArrayMapper implements TypeMapper {
    @Override
    public void unMap(ObjektMapper clazz, Field field, JSONObject jsObject) {
        try {
            field.set(clazz, jsObject.get(field.getName()).toString().replaceFirst("\\[", "").replaceFirst("\\]", "").replaceAll("\"", "").split(","));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
