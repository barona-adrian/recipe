package com.foodapi.model;

import java.util.List;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class UnusedIngredients {
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