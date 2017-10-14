//package com.foodapi;
//
//import com.google.gson.GsonBuilder;
//import com.mashape.unirest.http.JsonNode;
//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;
//import com.foodapi.model.FindByIDResponse;
//import com.foodapi.model.FindByIngredientsResponse;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.foodapi.constants.FindByIDConstants.stepBreakdown;
//import static com.foodapi.constants.FindByIngredientsConstants.*;
//
//public class Main {
//
////    private static SessionState.Client client;
//
////pepper, cheese , chicken breast
//
//
//
//    public static void main(String[] args) throws Exception {
////        client = ClientBuilder.newClient();
//        System.out.println("Start search");
//
//
//        List<String> ing = new ArrayList<>();
//        ing.add("apples");
//        ing.add("flour");
//        ing.add("sugar");
//
//        HttpResponse<JsonNode> response = getRecipes(ing);
//
//
//        FindByIngredientsResponse[] ingredients = new GsonBuilder().create().fromJson(response.getBody().getArray().toString(), FindByIngredientsResponse[].class);
//
////        List<FindByIngredientsResponse> findByIngredients = new ArrayList<>();
//
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(ingredients));
//
//        System.out.println("End Search");
//
//        System.out.println();
//        System.out.println();
//        System.out.println();
//
//        System.out.println("Begin Getting Instructions");
//
//        System.out.println();
//
//        String id = ingredients[0].getId();
//
//        HttpResponse<JsonNode> response2 = getInstruction(id);
//
//        FindByIDResponse[] instructions = new GsonBuilder().create().fromJson(response2.getBody().getArray().toString(), FindByIDResponse[].class);
//
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(instructions));
//
//        System.out.println("End Instructions");
//
//
//
//
//
//
//    }
//
//    private static HttpResponse<JsonNode> getRecipes(List<String> ingredients) throws Exception {
//        String url = generateFindByIngredientsURL(ingredients);
//        return makeGetRequest(url);
//    }
//
//    private static HttpResponse<JsonNode> makeGetRequest(String url) throws UnirestException {
//        HttpResponse<JsonNode> response = Unirest.get(url)
//                .header("X-Mashape-Key", "Drtz8kS0xZmshVqmhP1WuL11rRvzp1Ho53ajsnEtEG4u6Jrp26")
//                .header("Accept", "application/json")
//                .asJson();
//        return response;
//    }
//
//    private static String generateFindByIngredientsURL(List<String> ingredients) throws Exception {
//        if(ingredients.size() < 1){
//            throw new Exception("No ingredients were given!! How did it even get here??");
//        }
//
//        String begUrl = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?";
//        String fill = "fillIngredients=" + fillIngredients;
//        String lim = "limitLicense=" + limitLicense;
//        String num = "number=" + number;
//        String rank = "ranking=" + ranking;
//
//        StringBuilder ing = new StringBuilder();
//
//        ing.append(getIngredient(ingredients.get(0)));
//
//        for(int i = 1 ; i < ingredients.size() ; i++){
//            ing.append(ingredientDelimiter + getIngredient(ingredients.get(i)));
//        }
//
//        String ingr = "ingredients=" + ing.toString();
//
//        StringBuilder sb = new StringBuilder();
//        sb.append(begUrl);
//        sb.append(fill).append("&");
//        sb.append(ingr).append("&");
//        sb.append(lim).append("&");
//        sb.append(num).append("&");
//        sb.append(rank);
//
//        return sb.toString();
//    }
//
//    //if an ingredient has more than one word in it, it will add a "+" in between each word
//    private static String getIngredient(String ingredient){
//        StringBuilder ingredients = new StringBuilder();
//        String[] ings = ingredient.split(" ");
//        ingredients.append(ings[0]);
//
//        for(int i = 1 ; i < ings.length ; i++){
//            ingredients.append("+" + ings[i]);
//        }
//String s;
//        return ingredients.toString();
//    }
//
//
//
//
//
//    private static HttpResponse<JsonNode> getInstruction(String id) throws Exception {
//        String url = generateFindByIDURL(id);
//        return makeGetRequest(url);
//    }
//
//    private static String generateFindByIDURL(String id) {
//        String stepBrk = "stepBreakdown=" + stepBreakdown; //true or false
//        String url = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/" + id + "/analyzedInstructions?" + stepBrk;
//        return url;
//    }
//
//
//}
