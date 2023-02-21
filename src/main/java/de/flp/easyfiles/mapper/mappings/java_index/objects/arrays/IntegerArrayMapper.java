package de.flp.easyfiles.mapper.mappings.java_index.objects.arrays;

import de.flp.easyfiles.mapper.ObjektMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Arrays;

@MapperInfo(clazz = Integer[].class)
public class IntegerArrayMapper implements TypeMapper {
    @Override
    public void unMap(ObjektMapper clazz, Field field, JSONObject jsObject) {
        String str = jsObject.get(field.getName()).toString(); // get the string from the JSON object
        Integer[] intArray = Arrays.stream(str.substring(1, str.length() - 1).split(",")).map(String::trim).map(Integer::parseInt).toArray(Integer[]::new); // convert the string to an integer array
        try {
            field.set(clazz, intArray);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
