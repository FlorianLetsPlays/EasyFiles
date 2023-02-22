package de.flp.easyfiles.mapper.mappings.custom_index;

import de.flp.easyfiles.mapper.ObjectMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.CustumIndex;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import de.flp.easyfiles.mapper.anotaions.MappingType;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;

@MapperInfo(clazz = HashMap.class, type = MappingType.CUSTOM_INDEX)
public class HashMapMapper implements TypeMapper {
    @Override
    public void unMap(ObjectMapper clazz, Field field, JSONObject jsObject) {
        HashMap<Object, Object> map = new HashMap<>();
        for (int i = 0; i < jsObject.getJSONArray(field.getName()).length(); i++) {
            ObjectMapper objectMapper = null;
            try {
                objectMapper = (ObjectMapper) field.getAnnotation(CustumIndex.class).types()[1].newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            String[] split = jsObject.getJSONArray(field.getName()).get(i).toString().split(":", 2);

            objectMapper.setMapped(split[1]);

            split[0] = split[0].replaceAll("\"", "");
            split[0] = split[0].replaceAll("\\{", "");

            Class<?> aClazz = field.getAnnotation(CustumIndex.class).types()[0];

            Object key = split[0];

            key = (aClazz.equals(Integer.class) || aClazz.equals(int.class)) ? Integer.parseInt(split[0]) : key;
            key = (aClazz.equals(Long.class) || aClazz.equals(long.class)) ? Long.parseLong(split[0]) : key;
            key = (aClazz.equals(Double.class) || aClazz.equals(double.class)) ? Double.parseDouble(split[0]) : key;
            key = (aClazz.equals(Float.class) || aClazz.equals(float.class)) ? Float.parseFloat(split[0]) : key;
            key = (aClazz.equals(Boolean.class) || aClazz.equals(boolean.class)) ? Boolean.parseBoolean(split[0]) : key;
            key = (aClazz.equals(Character.class) || aClazz.equals(char.class)) ? split[0].charAt(0) : key;

            //System.out.println(key + "  " + key.getClass().getName());

            map.put(key, objectMapper);

        }

        try {
            field.set(clazz, map);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
