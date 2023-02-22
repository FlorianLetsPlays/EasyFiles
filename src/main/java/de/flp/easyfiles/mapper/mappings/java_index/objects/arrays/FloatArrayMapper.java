package de.flp.easyfiles.mapper.mappings.java_index.objects.arrays;

import de.flp.easyfiles.mapper.ObjectMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Arrays;

@MapperInfo(clazz = Float[].class)
public class FloatArrayMapper implements TypeMapper {
    @Override
    public void unMap(ObjectMapper clazz, Field field, JSONObject jsObject) {
        String str = jsObject.get(field.getName()).toString();
        Float[] floatArray = Arrays.stream(str.substring(1, str.length() - 1).split(",")).map(String::trim).map(s -> {
            try {
                return Float.parseFloat(s);
            } catch (NumberFormatException e) {
                // handle invalid values as needed
                return 0.0f;
            }
        }).toArray(Float[]::new);

        try {
            field.set(clazz, floatArray);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
