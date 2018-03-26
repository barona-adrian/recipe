package com.deliciousapp.onetime.findbyingredients.model.output;


import java.util.List;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class FindByIngredientsResponse {
    String id;
    String title;
    String image;
    String imageType;
    String usedIngredientCount;
    String missedIngredientCount;
    List<Ingredient> missedIngredients;
    List<Ingredient> usedIngredients;
    List<Ingredient> unusedIngredients;
    String likes;
}
