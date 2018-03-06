package com.foodapi.model.search.request.recipe;

import java.util.List;

@lombok.Getter
@lombok.Setter
public class FindByIngredientsRequest {
    private List<String> ingredients;
    private String number;
    private String ranking;
    private final String fillIngredients = "true";
    private final String ingredientDelimiter = "%2C";
    private final String limitLicense = "false";
}
