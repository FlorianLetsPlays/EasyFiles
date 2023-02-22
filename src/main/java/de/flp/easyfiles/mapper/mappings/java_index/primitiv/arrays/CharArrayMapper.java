package de.flp.easyfiles.mapper.mappings.java_index.primitiv.arrays;

import de.flp.easyfiles.mapper.ObjectMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;

@MapperInfo(clazz = char[].class)
public class CharArrayMapper implements TypeMapper {
    @Override
    public void unMap(ObjectMapper clazz, Field field, JSONObject jsObject) {
        String str = jsObject.get(field.getName()).toString();
        char[] charArray = str.substring(1, str.length() - 1).toCharArray();

        try {
            field.set(clazz, charArray);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
