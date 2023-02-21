package de.flp.easyfiles.mapper.mappings.java_index.objects;

import de.flp.easyfiles.mapper.ObjektMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;

@MapperInfo(clazz = Double.class)
public class DoubleMapper implements TypeMapper {
    @Override
    public void unMap(ObjektMapper clazz, Field field, JSONObject jsObject) {
        try {
            field.set(clazz, Double.parseDouble(jsObject.get(field.getName()).toString()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
