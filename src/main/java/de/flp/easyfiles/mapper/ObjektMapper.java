package de.flp.easyfiles.mapper;

import de.flp.easyfiles.mapper.anotaions.CustumObject;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ObjektMapper {




    public JSONObject getMapped() {

        JSONObject jsObject = new JSONObject(2);

        for (Field field : getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);

                if (field.get(this) == null) continue;

                if (field.getAnnotation(CustumObject.class) != null) {

                    if (field.getType().equals(ArrayList.class) || field.getType().equals(LinkedList.class)) {
                        jsObject.put(field.getName(), ((List<ObjektMapper>) field.get(this)).stream().map(ObjektMapper::getMapped).toArray());
                    } else if (field.getType().equals(HashMap.class)) {
                        jsObject.put(field.getName(), ((HashMap<Object, ObjektMapper>) field.get(this)).entrySet().stream().map(entry -> new JSONObject().put(entry.getKey().toString(), entry.getValue().getMapped())).toArray());
                    } else {
                        jsObject.put(field.getName(), ((ObjektMapper) field.get(this)).getMapped());
                    } continue;
                }


                jsObject.put(field.getName(), field.get(this));
            } catch (Exception a) {
                a.printStackTrace();
            }
        } return jsObject;
    }

    public void setMapped(String json) {
        JSONObject jsObject = new JSONObject(json);
        for (Field field : getClass().getDeclaredFields()) {
            try {

                field.setAccessible(true);

                if (!jsObject.has(field.getName())) continue;

                if (field.getAnnotation(CustumObject.class) != null) {

                    if (MappingRegister.get().getCustomMapperMap().containsKey(field.getType())) {
                        MappingRegister.get().getCustomMapperMap().get(field.getType()).unMap(this, field, jsObject);
                    } else {
                        MappingRegister.get().getCustomMapperMap().get(ObjektMapper.class).unMap(this, field, jsObject);
                    }

                } else {
                    if (MappingRegister.get().getJavaMapperMap().containsKey(field.getType())) {
                        MappingRegister.get().getJavaMapperMap().get(field.getType()).unMap(this, field, jsObject);
                    } else {
                        MappingRegister.get().getJavaMapperMap().get(Object.class).unMap(this, field, jsObject);
                    }

                }

            } catch (Exception a) {
                a.printStackTrace();
            }
        }
    }

}
