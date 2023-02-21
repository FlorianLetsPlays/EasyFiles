package de.flp.easyfiles.mapper.mappings.java_index.primitiv.arrays;

import de.flp.easyfiles.mapper.ObjektMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Arrays;

@MapperInfo(clazz = short[].class)
public class ShortArrayMapper implements TypeMapper {
    @Override
    public void unMap(ObjektMapper clazz, Field field, JSONObject jsObject) {
        String str = jsObject.get(field.getName()).toString();
        Short[] shortArray = Arrays.stream(str.substring(1, str.length() - 1).split(",")).map(String::trim).map(s -> {
            try {
                return Short.parseShort(s);
            } catch (NumberFormatException e) {
                // handle invalid values as needed
                return (short) 0;
            }
        }).toArray(Short[]::new);

        short[] shortArrayPrimitive = new short[shortArray.length];
        for (int i = 0; i < shortArray.length; i++) {
            shortArrayPrimitive[i] = shortArray[i];
        }

        try {
            field.set(clazz, shortArrayPrimitive);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
