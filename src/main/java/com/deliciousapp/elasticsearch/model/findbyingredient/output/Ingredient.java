package com.deliciousapp.elasticsearch.model.findbyingredient.output;


import java.util.List;

@lombok.Getter
@lombok.Setter
public class Ingredient {
    String id;
    String amount;
    String unit;
    String unitLong;
    String unitShort;
    String aisle;
    String name;
    String originalString;
    List<String> metaInformation;
    String extendedName;
    String image;
}
