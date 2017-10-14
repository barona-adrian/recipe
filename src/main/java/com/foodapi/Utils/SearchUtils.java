package com.foodapi.Utils;

import com.foodapi.model.FindByIngredientsRequest;
import com.foodapi.model.FindByIngredientsResponse;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchUtils {

    private List<String> ingredients;
    private String number;
    private String ranking;
    private String fillIngredients;
    private String ingredientDelimiter;
    private String limitLicense;

    public List<FindByIngredientsResponse> findRecipes(FindByIngredientsRequest request) throws Exception {
        this.ingredients = request.getIngredients();
        this.number = request.getNumber();
        this.ranking = request.getRanking();
        this.fillIngredients = request.getFillIngredients();
        this.ingredientDelimiter = request.getIngredientDelimiter();
        this.limitLicense = request.getLimitLicense();

        return getRecipe();
    }

    private List<FindByIngredientsResponse> getRecipe() throws Exception {
        HttpResponse<JsonNode> response = getRecipes();

        FindByIngredientsResponse[] ingredients = new GsonBuilder().create().fromJson(response.getBody().getArray().toString(), FindByIngredientsResponse[].class);

        return new ArrayList<>(Arrays.asList(ingredients));
    }









    private HttpResponse<JsonNode> getRecipes() throws Exception {
        String url = generateFindByIngredientsURL();
        return makeGetRequest(url);
    }

    private HttpResponse<JsonNode> makeGetRequest(String url) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(url)
                .header("X-Mashape-Key", "Drtz8kS0xZmshVqmhP1WuL11rRvzp1Ho53ajsnEtEG4u6Jrp26")
                .header("Accept", "application/json")
                .asJson();
        return response;
    }

    private String generateFindByIngredientsURL() throws Exception {
        if(this.ingredients.size() < 1){
            throw new Exception("No ingredients were given!! How did it even get here??");
        }

        String begUrl = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?";
        String fill = "fillIngredients=" + this.fillIngredients;
        String lim = "limitLicense=" + this.limitLicense;
        String num = "number=" + this.number;
        String rank = "ranking=" + this.ranking;

        StringBuilder ing = new StringBuilder();

        ing.append(getIngredient(ingredients.get(0)));

        for(int i = 1 ; i < ingredients.size() ; i++){
            ing.append(this.ingredientDelimiter + getIngredient(this.ingredients.get(i)));
        }

        String ingr = "ingredients=" + ing.toString();

        StringBuilder sb = new StringBuilder();
        sb.append(begUrl);
        sb.append(fill).append("&");
        sb.append(ingr).append("&");
        sb.append(lim).append("&");
        sb.append(num).append("&");
        sb.append(rank);

        return sb.toString();
    }

    private String getIngredient(String ingredient) {
        StringBuilder ingredients = new StringBuilder();
        String[] ings = ingredient.split(" ");
        ingredients.append(ings[0]);

        for(int i = 1 ; i < ings.length ; i++){
            ingredients.append("+" + ings[i]);
        }

        return ingredients.toString();
    }

}
