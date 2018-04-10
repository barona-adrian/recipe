package com.deliciousapp.elasticsearch.model.findbyingredient.output;


import java.util.List;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class FindByIngredientsOutput {

    String id;
    String title;
    String image;
    String usedIngredientCount;
    String missedIngredientCount;
    List<Ingredient> missedIngredients;
    List<Ingredient> usedIngredients;
    List<Ingredient> unusedIngredients;
    String likes;

}
