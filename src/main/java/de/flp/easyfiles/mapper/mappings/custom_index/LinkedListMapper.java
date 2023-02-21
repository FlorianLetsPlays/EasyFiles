package de.flp.easyfiles.mapper.mappings.custom_index;

import de.flp.easyfiles.mapper.ObjektMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.CustumObject;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import de.flp.easyfiles.mapper.anotaions.MappingType;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

@MapperInfo(clazz = LinkedList.class, type = MappingType.CUSTOM_INDEX)
public class LinkedListMapper implements TypeMapper {
    @Override
    public void unMap(ObjektMapper clazz, Field field, JSONObject jsObject) {
        List<ObjektMapper> list = new LinkedList<>();
        for (int i = 0; i < jsObject.getJSONArray(field.getName()).length(); i++) {
            ObjektMapper objektMapper = null;
            try {
                objektMapper = (ObjektMapper) field.getDeclaredAnnotation(CustumObject.class).types()[0].newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            objektMapper.setMapped(jsObject.getJSONArray(field.getName()).get(i).toString());
            list.add(objektMapper);
        }

        try {
            field.set(clazz, list);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
