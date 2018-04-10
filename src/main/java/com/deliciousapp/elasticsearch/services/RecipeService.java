package com.deliciousapp.elasticsearch.services;

import com.deliciousapp.elasticsearch.model.findbyingredient.output.FindByElasticIngredientsResponse;
import com.deliciousapp.onetime.findByID.model.output.AnalyzedInstructionsResponse;
import com.deliciousapp.elasticsearch.utils.SearchByIngredientUtils;
import com.deliciousapp.elasticsearch.model.findbyingredient.input.FindByIngredientsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class RecipeService {

    @Autowired
    SearchByIngredientUtils searchUtils;

    public List<FindByElasticIngredientsResponse> findByIngredients(FindByIngredientsRequest request) throws Exception {
        return searchUtils.findByIngredients(request);
    }

    public AnalyzedInstructionsResponse analyzedInstructions(String id) throws Exception {
        return searchUtils.analyzedInstructions(id);
    }


}
