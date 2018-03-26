package com.deliciousapp.elasticsearch.utils;

import com.deliciousapp.elasticsearch.annotations.ElasticMapping;
import com.deliciousapp.elasticsearch.enums.ElasticMappingEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

@Slf4j
@Component
public class Utils {

    public static final HashMap<String, Set<String>> INGREDIENT_FIELDS = new HashMap<>();

    public Utils(Environment environment){

    }

    public static String[] getAllIngredientFields(String type){

        String[] str = null;
        Set<String> fields = INGREDIENT_FIELDS.get(type);
        if(fields != null){
            str = new String[fields.size()];
            str = fields.toArray(str);
        }

        return null;
    }

    public ElasticMappingEnum[] getElasticFieldMapping(Class cl, String fieldName) throws NoSuchFieldException {

        try {
            Field field = cl.getDeclaredField(fieldName);
            Annotation[] annotations = field.getDeclaredAnnotations();
            for(Annotation annotation : annotations){
                if(annotation instanceof ElasticMapping){
                    ElasticMapping elasticMapping = (ElasticMapping) annotation;
                    return elasticMapping.value();
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new NoSuchFieldException(e.getMessage());
        }

        return null;
    }

}
