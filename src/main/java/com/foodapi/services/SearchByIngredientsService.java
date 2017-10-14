package com.foodapi.services;

import com.foodapi.Utils.SearchUtils;
import com.foodapi.model.FindByIngredientsRequest;
import com.foodapi.model.FindByIngredientsResponse;

import java.util.List;

public class SearchByIngredientsService {

    private SearchUtils searchUtils = new SearchUtils();

    public List<FindByIngredientsResponse> findRecipes(FindByIngredientsRequest request) throws Exception {
        return searchUtils.findRecipes(request);
    }


}
