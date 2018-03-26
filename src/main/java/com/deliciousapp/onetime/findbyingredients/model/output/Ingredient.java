package com.deliciousapp.onetime.findbyingredients.model.output;

import java.util.List;

@lombok.Setter
@lombok.Getter
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
