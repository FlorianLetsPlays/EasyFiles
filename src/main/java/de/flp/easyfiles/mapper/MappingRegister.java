package de.flp.easyfiles.mapper;

import de.flp.easyfiles.mapper.anotaions.MapperInfo;
import de.flp.easyfiles.mapper.anotaions.MappingType;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

import java.util.HashMap;

public class MappingRegister {

    private static MappingRegister instance;
    private static HashMap<Class<?>, TypeMapper> javaMapperMap;
    private static HashMap<Class<?>, TypeMapper> customMapperMap;

    private MappingRegister(String folder) {

        javaMapperMap = new HashMap<>();
        customMapperMap = new HashMap<>();

        ClassGraph classGraph = new ClassGraph().enableAllInfo().verbose(false).acceptPackages(folder);
        try (ScanResult scanResult = classGraph.scan()) {
            scanResult.getClassesImplementing(TypeMapper.class).loadClasses().forEach(clazz -> {
                try {
                    TypeMapper typeMapper = (TypeMapper) clazz.newInstance();
                    //System.out.println(clazz.getName() + " registerd");

                    if (clazz.getAnnotation(MapperInfo.class).type() == MappingType.CUSTOM_INDEX) {
                        for (Class<?> aClass : clazz.getAnnotation(MapperInfo.class).clazz()) {
                            customMapperMap.put(aClass, typeMapper);
                        }


                    } else if (clazz.getAnnotation(MapperInfo.class).type() == MappingType.JAVA_INDEX) {
                        for (Class<?> aClass : clazz.getAnnotation(MapperInfo.class).clazz()) {
                            javaMapperMap.put(aClass, typeMapper);
                        }
                    }

                } catch (InstantiationException | SecurityException | IllegalAccessException |
                         IllegalArgumentException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static MappingRegister get() {

        if (instance == null) {
            instance = new MappingRegister("de.flame_hosting.config.api.mapper.mappings");
        }

        return instance;
    }

    public HashMap<Class<?>, TypeMapper> getJavaMapperMap() {
        return javaMapperMap;
    }

    public HashMap<Class<?>, TypeMapper> getCustomMapperMap() {
        return customMapperMap;
    }

}
