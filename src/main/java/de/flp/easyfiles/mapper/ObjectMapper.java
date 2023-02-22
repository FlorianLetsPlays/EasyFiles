package de.flp.easyfiles.mapper;

import de.flp.easyfiles.mapper.anotaions.CustumIndex;
import de.flp.easyfiles.mapper.anotaions.JavaIndex;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ObjectMapper {




    public JSONObject getMapped() {

        JSONObject jsObject = new JSONObject();

        for (Field field : getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);

                if (field.get(this) == null) continue;

                if (field.getAnnotation(CustumIndex.class) != null) {

                    if (field.getType().equals(ArrayList.class) || field.getType().equals(LinkedList.class)) {
                        jsObject.put(field.getName(), ((List<ObjectMapper>) field.get(this)).stream().map(ObjectMapper::getMapped).toArray());
                    } else if (field.getType().equals(HashMap.class)) {
                        jsObject.put(field.getName(), ((HashMap<Object, ObjectMapper>) field.get(this)).entrySet().stream().map(entry -> new JSONObject().put(entry.getKey().toString(), entry.getValue().getMapped())).toArray());
                    } else {
                        jsObject.put(field.getName(), ((ObjectMapper) field.get(this)).getMapped());
                    } continue;
                }


                jsObject.put(field.getName(), field.get(this));
            } catch (Exception a) {
                a.printStackTrace();
            }
        } return jsObject;
    }

    public Boolean setMapped(String json, HashMap<String, Object> defaults) {
        Boolean returning = false;
        JSONObject jsObject = new JSONObject(json);
        for (Field field : getClass().getDeclaredFields()) {
            try {

                field.setAccessible(true);



                if (field.getAnnotation(CustumIndex.class) != null) {

                    if (!jsObject.has(field.getName())) {

                        if (field.getAnnotation(CustumIndex.class).nullable()) {
                            continue;
                        }

                        if(defaults.containsKey(field.getName())) {
                            field.set(this, defaults.get(field.getName()));
                        }
                        returning = true;
                        continue;
                    }


                    if (MappingRegister.get().getCustomMapperMap().containsKey(field.getType())) {
                        MappingRegister.get().getCustomMapperMap().get(field.getType()).unMap(this, field, jsObject);
                    } else {
                        MappingRegister.get().getCustomMapperMap().get(ObjectMapper.class).unMap(this, field, jsObject);
                    }

                } else if (field.getAnnotation(JavaIndex.class) != null){

                    if (!jsObject.has(field.getName())) {

                        if (field.getAnnotation(JavaIndex.class).nullable()) {
                            continue;
                        }

                        if(defaults.containsKey(field.getName())) {
                            field.set(this, defaults.get(field.getName()));
                        }
                        returning = true;
                        continue;
                    }

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
        return returning;
    }

    public void setMapped(String json) {
        JSONObject jsObject = new JSONObject(json);
        for (Field field : getClass().getDeclaredFields()) {
            try {

                field.setAccessible(true);

                if (!jsObject.has(field.getName())) continue;

                if (field.getAnnotation(CustumIndex.class) != null) {

                    if (MappingRegister.get().getCustomMapperMap().containsKey(field.getType())) {
                        MappingRegister.get().getCustomMapperMap().get(field.getType()).unMap(this, field, jsObject);
                    } else {
                        MappingRegister.get().getCustomMapperMap().get(ObjectMapper.class).unMap(this, field, jsObject);
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
