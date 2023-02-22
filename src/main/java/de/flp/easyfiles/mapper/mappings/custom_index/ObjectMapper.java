package de.flp.easyfiles.mapper.mappings.custom_index;

import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import de.flp.easyfiles.mapper.anotaions.MappingType;
import org.json.JSONObject;

import java.lang.reflect.Field;

@MapperInfo(clazz = de.flp.easyfiles.mapper.ObjectMapper.class, type = MappingType.CUSTOM_INDEX)
public class ObjectMapper implements TypeMapper {
    @Override
    public void unMap(de.flp.easyfiles.mapper.ObjectMapper clazz, Field field, JSONObject jsObject) {
        de.flp.easyfiles.mapper.ObjectMapper objectMapper = null;
        try {
            objectMapper = (de.flp.easyfiles.mapper.ObjectMapper) field.getType().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        objectMapper.setMapped(jsObject.getJSONObject(field.getName()).toString());
        try {
            field.set(clazz, objectMapper);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
