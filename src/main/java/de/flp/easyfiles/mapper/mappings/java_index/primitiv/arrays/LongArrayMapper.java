package de.flp.easyfiles.mapper.mappings.java_index.primitiv.arrays;

import de.flp.easyfiles.mapper.ObjektMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Arrays;

@MapperInfo(clazz = long[].class)
public class LongArrayMapper implements TypeMapper {
    @Override
    public void unMap(ObjektMapper clazz, Field field, JSONObject jsObject) {
        String str = jsObject.get(field.getName()).toString();
        long[] longArray = Arrays.stream(str.substring(1, str.length() - 1).split(",")).mapToLong(Long::parseLong).toArray();

        try {
            field.set(clazz, longArray);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
