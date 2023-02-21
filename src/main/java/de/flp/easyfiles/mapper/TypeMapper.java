package de.flp.easyfiles.mapper;

import org.json.JSONObject;

import java.lang.reflect.Field;

public interface TypeMapper {

    public void unMap(ObjektMapper clazz, Field field, JSONObject jsObject);

}
