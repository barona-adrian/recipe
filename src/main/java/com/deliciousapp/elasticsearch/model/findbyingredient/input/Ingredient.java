package com.deliciousapp.elasticsearch.model.findbyingredient.input;


import com.deliciousapp.elasticsearch.annotations.ElasticMapping;
import static com.deliciousapp.elasticsearch.enums.ElasticMappingEnum.*;


@lombok.Getter
@lombok.Setter
public class Ingredient {

    @ElasticMapping({
            title,
            ingredients_aisle,
            ingredients_name,
            ingredients_originalString,
            ingredients_metaInformation,
            ingredients_extendedName
    })
    private String name;

    private boolean mandatory;
}
