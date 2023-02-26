package de.flp.easyfiles.mapper.mappings.java_index;

import de.flp.easyfiles.mapper.ObjectMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.JavaIndex;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@MapperInfo(clazz = {
        ArrayList.class,
        LinkedList.class,
})
public class ListMapper implements TypeMapper {
    @Override
    public void unMap(ObjectMapper clazz, Field field, JSONObject jsObject) {
        List<Object> list;

        if (field.getType().equals(ArrayList.class)) {
            list = new ArrayList<>();
        } else {
            list = new LinkedList<>();
        }

        Class<?> aClazz = field.getAnnotation(JavaIndex.class).types()[0];

        String jsObjectString = jsObject.get(field.getName()).toString();

        jsObjectString = jsObjectString.replaceFirst("\\[", "").replaceFirst("]", "");

        Arrays.asList(jsObjectString.split(",")).forEach(s -> {

            s = s.replaceAll("\"", "");

            Object key = s;

            System.out.println(s);

            key = (aClazz.equals(Integer.class) || aClazz.equals(int.class)) ? Integer.parseInt(s) : key;
            key = (aClazz.equals(Long.class) || aClazz.equals(long.class)) ? Long.parseLong(s) : key;
            key = (aClazz.equals(Double.class) || aClazz.equals(double.class)) ? Double.parseDouble(s) : key;
            key = (aClazz.equals(Float.class) || aClazz.equals(float.class)) ? Float.parseFloat(s) : key;
            key = (aClazz.equals(Boolean.class) || aClazz.equals(boolean.class)) ? Boolean.parseBoolean(s) : key;
            key = (aClazz.equals(Character.class) || aClazz.equals(char.class)) ? s.charAt(0) : key;

            list.add(key);
        });

        try {
            field.set(clazz, list);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
