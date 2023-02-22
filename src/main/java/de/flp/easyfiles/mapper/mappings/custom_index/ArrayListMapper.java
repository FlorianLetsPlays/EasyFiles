package de.flp.easyfiles.mapper.mappings.custom_index;

import de.flp.easyfiles.mapper.ObjectMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.CustumIndex;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import de.flp.easyfiles.mapper.anotaions.MappingType;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@MapperInfo(clazz = ArrayList.class, type = MappingType.CUSTOM_INDEX)
public class ArrayListMapper implements TypeMapper {
    @Override
    public void unMap(ObjectMapper clazz, Field field, JSONObject jsObject) {
        List<ObjectMapper> list = new ArrayList<>();
        for (int i = 0; i < jsObject.getJSONArray(field.getName()).length(); i++) {
            ObjectMapper objectMapper = null;
            try {
                objectMapper = (ObjectMapper) field.getAnnotation(CustumIndex.class).types()[0].newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            objectMapper.setMapped(jsObject.getJSONArray(field.getName()).get(i).toString());
            list.add(objectMapper);
        }

        try {
            field.set(clazz, list);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
