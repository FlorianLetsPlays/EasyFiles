package de.flp.easyfiles.mapper.anotaions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapperInfo {

    Class<?>[] clazz();

    MappingType type() default MappingType.JAVA_INDEX;

}
