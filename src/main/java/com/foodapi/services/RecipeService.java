package com.foodapi.services;

import com.foodapi.Utils.SearchUtils;
import com.foodapi.model.search.request.instructions.AnalyzedInstructionsRequest;
import com.foodapi.model.search.request.recipe.FindByIngredientsRequest;
import com.foodapi.model.search.response.instruction.AnalyzedInstructionsResponse;
import com.foodapi.model.search.response.recipe.FindByIngredientsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipeService {

    @Autowired
    SearchUtils searchUtils;

    public List<FindByIngredientsResponse> findByIngredients(FindByIngredientsRequest request) throws Exception {
        return searchUtils.findByIngredients(request);
    }

    public List<AnalyzedInstructionsResponse> analyzedInstructions(AnalyzedInstructionsRequest request) throws Exception {
        return searchUtils.analyzedInstructions(request);
    }


}
