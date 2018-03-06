package com.foodapi.model.search.response.recipe;


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
    List<MissedIngredients> missedIngredients;
    List<UsedIngredients> usedIngredients;
    List<UnusedIngredients> unusedIngredients;
    String likes;

}
