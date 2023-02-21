package de.flp.easyfiles.mapper.mappings.custom_index;

import de.flp.easyfiles.mapper.ObjektMapper;
import de.flp.easyfiles.mapper.TypeMapper;
import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import de.flp.easyfiles.mapper.anotaions.MappingType;
import org.json.JSONObject;

import java.lang.reflect.Field;

@MapperInfo(clazz = ObjektMapper.class, type = MappingType.CUSTOM_INDEX)
public class ObjectMapper implements TypeMapper {
    @Override
    public void unMap(ObjektMapper clazz, Field field, JSONObject jsObject) {
        ObjektMapper objektMapper = null;
        try {
            objektMapper = (ObjektMapper) field.getType().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        objektMapper.setMapped(jsObject.getJSONObject(field.getName()).toString());
        try {
            field.set(clazz, objektMapper);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
