package com.deliciousapp.elasticsearch.model.findbyingredient.input;

import java.util.List;

@lombok.Getter
@lombok.Setter
public class FindByIngredientsRequest {


    private List<String> ingredients;

    private List<Ingredient> ingredientList;

    private int itemsPerPage;




    private String number;
    private String ranking;
    private final String fillIngredients = "true";
    private final String ingredientDelimiter = "%2C";
    private final String limitLicense = "false";
}
