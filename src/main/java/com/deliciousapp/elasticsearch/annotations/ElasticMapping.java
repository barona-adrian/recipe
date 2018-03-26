package com.deliciousapp.elasticsearch.annotations;

import com.deliciousapp.elasticsearch.enums.ElasticMappingEnum;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticMapping {

    ElasticMappingEnum[] value();

}
