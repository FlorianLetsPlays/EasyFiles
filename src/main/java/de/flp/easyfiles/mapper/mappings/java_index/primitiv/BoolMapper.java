package de.flp.easyfiles.mapper.mappings.java_index.primitiv;

import de.flp.easyfiles.mapper.ObjektMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;

@MapperInfo(clazz = boolean.class)
public class BoolMapper implements TypeMapper {
    @Override
    public void unMap(ObjektMapper clazz, Field field, JSONObject jsObject) {

        try {
            field.set(clazz, jsObject.getBoolean(field.getName()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
