package de.flp.easyfiles.mapper.mappings.java_index.primitiv.arrays;

import de.flp.easyfiles.mapper.ObjektMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Arrays;

@MapperInfo(clazz = double[].class)
public class DoubleArrayMapper implements TypeMapper {
    @Override
    public void unMap(ObjektMapper clazz, Field field, JSONObject jsObject) {
        String str = jsObject.get(field.getName()).toString();
        double[] doubleArray = Arrays.stream(str.substring(1, str.length() - 1).split(",")).mapToDouble(Double::parseDouble).toArray();

        try {
            field.set(clazz, doubleArray);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
