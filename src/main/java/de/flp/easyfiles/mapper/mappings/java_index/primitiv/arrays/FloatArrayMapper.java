package de.flp.easyfiles.mapper.mappings.java_index.primitiv.arrays;

import de.flp.easyfiles.mapper.ObjektMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Arrays;

@MapperInfo(clazz = float[].class)
public class FloatArrayMapper implements TypeMapper {
    @Override
    public void unMap(ObjektMapper clazz, Field field, JSONObject jsObject) {
        String str = jsObject.get(field.getName()).toString();
        Float[] floatArray = Arrays.stream(str.substring(1, str.length() - 1).split(",")).map(String::trim).map(s -> {
            try {
                return Float.parseFloat(s);
            } catch (NumberFormatException e) {
                // handle invalid values as needed
                return 0.0f;
            }
        }).toArray(Float[]::new);

        float[] floatArrayPrimitive = new float[floatArray.length];
        for (int i = 0; i < floatArray.length; i++) {
            floatArrayPrimitive[i] = floatArray[i];
        }

        try {
            field.set(clazz, floatArrayPrimitive);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
