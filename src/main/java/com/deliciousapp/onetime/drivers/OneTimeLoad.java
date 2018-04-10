package com.deliciousapp.onetime.drivers;

import com.deliciousapp.elasticsearch.model.findbyingredient.output.Ingredient;
import com.deliciousapp.onetime.findByID.FindRecipeByID;
import com.deliciousapp.onetime.findByID.model.input.AnalyzedInstructionsRequest;
import com.deliciousapp.onetime.findByID.model.output.AnalyzedInstructionsResponse;
import com.deliciousapp.onetime.findbyingredients.model.output.FindByIngredientsResponse;
import com.deliciousapp.onetime.findbyingredients.FindByIngredients;
import com.deliciousapp.onetime.findbyingredients.model.input.FindByIngredientsRequest;

import com.deliciousapp.elasticsearch.model.findbyingredient.output.FindByElasticIngredientsResponse;

import io.searchbox.client.JestClient;

import java.util.*;

import com.deliciousapp.onetime.elasticupdate.ElasticUpdate;


public class OneTimeLoad {

    static ArrayList<String> ids;
    static Set<String> uniqueIds;

    final static private String[] food = {
            "chicken",
            "chicken,steak,black pepper,eggs",
            "fish",
            "fish,garclic,onion",
            "onion,green pepper,pepper,onion powder",
            "plantain,tomato,chicken",
            "meat",
            "beef,cinnamon,cheese",
            "chicken,cheese",
            "ribs,cheese",
            "rib,cheese,peppers,onion,steak",
            "ground beef",
            "flour",
            "rice,beans,meat,steak",
            "rice,beans,chicken",
            "rice,lentils,plantains,pepper,onion",
            "garlic,flour",
            "eggs,peppers,cauliflower,garlic",
            "carrot",
            "carrot,grape",
            "pickles,pickle",
            "zucchini,onion",
            "broccoli",
            "broccoli,cheese,ham",
            "broccoli,peppers,",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",

    };

    final static private String[] drink = {};

//    public static void main(String[] args) throws Exception {
//        ElasticUpdate load = new ElasticUpdate();
//        updateElastic(food, load, "Food");
//        updateElastic(drink, load, "Drink");
//    }

    public static void updateElastic(String[] ings, ElasticUpdate load, String type) throws Exception {
        ids = new ArrayList<>();
        for(int i = 0 ; i < ings.length ; i++){
            updateElasticIngredients(ings[i], load);
        }
        uniqueIds = new HashSet<>(ids);
        System.out.println("Count of all " + type + " ID's retrieved from searches     : " + ids.size());
        System.out.println("Unique count of all " + type +  " ID's updated into Elastic : " + uniqueIds.size());
    }

    private static void updateElasticIngredients(String food, ElasticUpdate load) throws Exception {
        FindByIngredients findByIngredients = new FindByIngredients();
        FindByIngredientsRequest find = new FindByIngredientsRequest();
        List<String> foods = Arrays.asList(food.split(","));
        find.setIngredients(foods);
        find.setNumber("100");
        find.setRanking("1");
        List<FindByIngredientsResponse> responses = findByIngredients.findByIngredients(find);
        System.out.println("'" + food + "' generated " + responses.size() + " responses.");

        for(FindByIngredientsResponse response : responses){
            FindRecipeByID recipe = new FindRecipeByID();
            AnalyzedInstructionsRequest req = new AnalyzedInstructionsRequest();
            req.setRecipeID(response.getId());

            AnalyzedInstructionsResponse instructionsResponse = null;
            try {
                instructionsResponse = recipe.analyzedInstructions(req);
                load.updateElasticRecipes(instructionsResponse, req.getRecipeID());
            } catch (NullPointerException ex){
                System.out.println(req.getRecipeID() + " id doesn't have a recipe :'(");
            }

            if(instructionsResponse != null) {
                String id = response.getId();
                String title = response.getTitle();
                String image = response.getImage();

                List<Ingredient> ingredients = new ArrayList<>();
                for (com.deliciousapp.onetime.findbyingredients.model.output.Ingredient ing : response.getMissedIngredients()) {
                    Ingredient ingredient = new Ingredient();

                    ingredient.setId(ing.getId());
                    ingredient.setUnit(ing.getUnit());
                    ingredient.setUnitLong(ing.getUnitLong());
                    ingredient.setUnitShort(ing.getUnitShort());
                    ingredient.setAisle(ing.getAisle());
                    ingredient.setName(ing.getName());
                    ingredient.setOriginalString(ing.getOriginalString());
                    ingredient.setMetaInformation(ing.getMetaInformation());
                    ingredient.setExtendedName(ing.getExtendedName());
                    ingredient.setImage(ing.getImage());

                    ingredients.add(ingredient);
                }

                FindByElasticIngredientsResponse elasticIngredientsResponse = new FindByElasticIngredientsResponse();
                elasticIngredientsResponse.setId(id);
                elasticIngredientsResponse.setTitle(title);
                elasticIngredientsResponse.setImage(image);
                elasticIngredientsResponse.setIngredients(ingredients);

                ids.add(id);

                load.updateElasticIngredients(elasticIngredientsResponse);
            }
        }
    }



}



















//findByIngredientsResponse.setId("5316830000000001");
//        findByIngredientsResponse.setTitle("Barbecue Chicken Quesadillas");
//        findByIngredientsResponse.setImage("https://spoonacular.com/recipeImages/Barbecue-Chicken-Quesadillas-531683.jpg");
//
//
//        List<Ingredient> ingredients = new ArrayList<>();
//        Ingredient ingredient = new Ingredient();
//
//        ingredient.setId("18364");
//        ingredient.setAmount("12");
//        ingredient.setUnit("");
//        ingredient.setUnitLong("");
//        ingredient.setUnitShort("");
//        ingredient.setAisle("Bakery/Bread;Pasta and Rice;Ethnic Foods");
//        ingredient.setName("tortillas");
//        ingredient.setOriginalString("12 tortillas");
//        ingredient.setMetaInformation(new ArrayList<>());
//        ingredient.setExtendedName("");
//        ingredient.setImage("https://spoonacular.com/cdn/ingredients_100x100/flour-tortillas.jpg");
//
//        Ingredient ingredient2 = new Ingredient();
//
//        ingredient2.setId("1041009");
//        ingredient2.setAmount("1.5");
//        ingredient2.setUnit("cups");
//        ingredient2.setUnitLong("cups");
//        ingredient2.setUnitShort("cup");
//        ingredient2.setAisle("Cheese");
//        ingredient2.setName("cheese");
//        ingredient2.setOriginalString("1½ cups cheese");
//        ingredient2.setMetaInformation(new ArrayList<>());
//        ingredient2.setExtendedName("");
//        ingredient2.setImage("https://spoonacular.com/cdn/ingredients_100x100/cheddar-cheese.jpg");
//
//        Ingredient ingredient3 = new Ingredient();
//
//        List<String> metaInfo = new ArrayList<>();
//        metaInfo.add("shredded");
//
//        ingredient3.setId("5006");
//        ingredient3.setAmount("2");
//        ingredient3.setUnit("cups");
//        ingredient3.setUnitLong("cups");
//        ingredient3.setUnitShort("cup");
//        ingredient3.setAisle("Meat");
//        ingredient3.setName("chicken");
//        ingredient3.setOriginalString("2 cups shredded barbecue chicken");
//        ingredient3.setMetaInformation(metaInfo);
//        ingredient3.setExtendedName("shredded chicken");
//        ingredient3.setImage("https://spoonacular.com/cdn/ingredients_100x100/whole-chicken.jpg");
//
//
//        ingredients.add(ingredient);
//        ingredients.add(ingredient2);
//        ingredients.add(ingredient3);
//
//
//        findByIngredientsResponse.setIngredients(ingredients);
