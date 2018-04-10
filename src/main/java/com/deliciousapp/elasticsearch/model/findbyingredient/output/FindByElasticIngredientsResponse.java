package com.deliciousapp.elasticsearch.model.findbyingredient.output;
import java.util.List;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class FindByElasticIngredientsResponse {
    String id;
    String title;
    String image;
    List<Ingredient> ingredients;
    List<String> matchedIngredients;
}
