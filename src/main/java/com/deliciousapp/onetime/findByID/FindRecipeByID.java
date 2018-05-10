package com.deliciousapp.onetime.findByID;

import com.deliciousapp.onetime.findByID.model.input.AnalyzedInstructionsRequest;
import com.deliciousapp.onetime.findByID.model.output.AnalyzedInstructionsResponse;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class FindRecipeByID {

    private String recipeID;
    private boolean stepBreakdown;

    public AnalyzedInstructionsResponse analyzedInstructions(AnalyzedInstructionsRequest request) throws Exception {
        this.recipeID = request.getRecipeID();
        this.stepBreakdown = request.isStepBreakdown();
        return analyzedInstruction();
    }

    private AnalyzedInstructionsResponse analyzedInstruction() throws Exception {
        HttpResponse<JsonNode> response = getAnalyzedInstruction();

        System.out.println("get body:\n" + response.getBody().toString());
        System.out.println("get array:\n" + response.getBody().getArray().toString());
        System.out.println("get array 0:\n" + response.getBody().getArray().get(0).toString());
//        System.out.println("get object:\n" + response.getBody().getObject().toString());

        AnalyzedInstructionsResponse instructions = new GsonBuilder().create().fromJson(response.getBody().getArray().get(0).toString(), AnalyzedInstructionsResponse.class);
        return instructions;
    }

    private HttpResponse<JsonNode> getAnalyzedInstruction() throws Exception {
        String url = generateAnalyzedInstructionURL();
        return makeGetRequest(url);
    }

    private String generateAnalyzedInstructionURL() throws Exception {
        if(this.recipeID == null || this.recipeID.equals("") || this.recipeID.chars().allMatch(Character::isLetter)){
            throw new Exception("No ID was given!! How did it even get here??");
        }

        String id = this.recipeID;
        boolean stepBreakdown = this.stepBreakdown;
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/" + id + "/analyzedInstructions?stepBreakdown=" + stepBreakdown;
        return url;
    }

    private HttpResponse<JsonNode> makeGetRequest(String url) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(url)
                .header("X-Mashape-Key", "jbBdXOOCU9mshpJwiHbTJha35RABp13UU5cjsnLKFMTyuYSPAd")
                .header("X-Mashape-Host", "spoonacular-recipe-food-nutrition-v1.p.mashape.com")
                .asJson();
        return response;
    }

}
