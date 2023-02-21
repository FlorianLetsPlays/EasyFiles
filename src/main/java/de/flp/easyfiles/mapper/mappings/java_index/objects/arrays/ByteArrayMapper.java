package de.flp.easyfiles.mapper.mappings.java_index.objects.arrays;

import de.flp.easyfiles.mapper.ObjektMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Arrays;

@MapperInfo(clazz = Byte[].class)
public class ByteArrayMapper implements TypeMapper {
    @Override
    public void unMap(ObjektMapper clazz, Field field, JSONObject jsObject) {
        String str = jsObject.get(field.getName()).toString();
        Byte[] byteArray = Arrays.stream(str.substring(1, str.length() - 1).split(",")).map(String::trim).map(s -> {
            try {
                return Byte.parseByte(s);
            } catch (NumberFormatException e) {
                // handle invalid values as needed
                return (byte) 0;
            }
        }).toList().toArray(new Byte[0]);

        try {
            field.set(clazz, byteArray);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
