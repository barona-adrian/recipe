package com.foodapi.model;

import java.util.List;

@lombok.Getter
@lombok.Setter
public class FindByIngredientsRequest {

    List<String> ingredients;
    String number;
    String ranking;
    final String fillIngredients = "true";
    final String ingredientDelimiter = "%2C";
    final String limitLicense = "false";
}
