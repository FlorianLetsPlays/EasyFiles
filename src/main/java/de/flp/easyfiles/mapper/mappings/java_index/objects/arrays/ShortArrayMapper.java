package de.flp.easyfiles.mapper.mappings.java_index.objects.arrays;

import de.flp.easyfiles.mapper.ObjectMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Arrays;

@MapperInfo(clazz = Short[].class)
public class ShortArrayMapper implements TypeMapper {
    @Override
    public void unMap(ObjectMapper clazz, Field field, JSONObject jsObject) {
        String str = jsObject.get(field.getName()).toString();
        Short[] shortArray = Arrays.stream(str.substring(1, str.length() - 1).split(",")).map(String::trim).map(s -> {
            try {
                return Short.parseShort(s);
            } catch (NumberFormatException e) {
                // handle invalid values as needed
                return (short) 0;
            }
        }).toArray(Short[]::new);

        try {
            field.set(clazz, shortArray);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
