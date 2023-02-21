package de.flp.easyfiles.mapper.mappings.java_index.primitiv.arrays;

import de.flp.easyfiles.mapper.ObjektMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Arrays;

@MapperInfo(clazz = byte[].class)
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

        byte[] byteArrayPrimitive = new byte[byteArray.length];

        // Copy the elements from the Byte[] array to the new byte[] array using a loop

        for (int i = 0; i < byteArray.length; i++) {
            byteArrayPrimitive[i] = byteArray[i];
        }

        try {
            field.set(clazz, byteArrayPrimitive);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
