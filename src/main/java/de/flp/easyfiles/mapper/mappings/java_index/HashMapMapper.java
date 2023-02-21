package de.flp.easyfiles.mapper.mappings.java_index;

import de.flp.easyfiles.mapper.ObjektMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.JavaIndex;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;

@MapperInfo(clazz = HashMap.class)
public class HashMapMapper implements TypeMapper {
    @Override
    public void unMap(ObjektMapper clazz, Field field, JSONObject jsObject) {
        try {

            Class<?> aClazz = field.getAnnotation(JavaIndex.class).types()[0];
            Class<?> bClazz = field.getAnnotation(JavaIndex.class).types()[1];

            field.set(clazz, new HashMap<Object, Object>() {{
                for (String s : jsObject.get(field.getName()).toString().split(",")) {
                    String[] split = s.split(":");
                    split[0] = split[0].replaceAll("\"", "");
                    split[0] = split[0].replaceAll("\\{", "");
                    split[1] = split[1].replaceAll("\\}", "");

                    Object key = split[0];

                    key = (aClazz.equals(Integer.class) || aClazz.equals(int.class)) ? Integer.parseInt(split[0]) : key;
                    key = (aClazz.equals(Long.class) || aClazz.equals(long.class)) ? Long.parseLong(split[0]) : key;
                    key = (aClazz.equals(Double.class) || aClazz.equals(double.class)) ? Double.parseDouble(split[0]) : key;
                    key = (aClazz.equals(Float.class) || aClazz.equals(float.class)) ? Float.parseFloat(split[0]) : key;
                    key = (aClazz.equals(Boolean.class) || aClazz.equals(boolean.class)) ? Boolean.parseBoolean(split[0]) : key;
                    key = (aClazz.equals(Character.class) || aClazz.equals(char.class)) ? split[0].charAt(0) : key;

                    Object value = split[0];

                    value = (bClazz.equals(Integer.class) || bClazz.equals(int.class)) ? Integer.parseInt(split[0]) : value;
                    value = (bClazz.equals(Long.class) || bClazz.equals(long.class)) ? Long.parseLong(split[0]) : value;
                    value = (bClazz.equals(Double.class) || bClazz.equals(double.class)) ? Double.parseDouble(split[0]) : value;
                    value = (bClazz.equals(Float.class) || bClazz.equals(float.class)) ? Float.parseFloat(split[0]) : value;
                    value = (bClazz.equals(Boolean.class) || bClazz.equals(boolean.class)) ? Boolean.parseBoolean(split[0]) : value;
                    value = (bClazz.equals(Character.class) || bClazz.equals(char.class)) ? split[0].charAt(0) : value;

                    put(key, value);
                }
            }});
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
