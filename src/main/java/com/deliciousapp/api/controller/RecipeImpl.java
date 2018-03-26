//package com.food.api.controller;
//
//import com.food.api.model.search.request.instructions.AnalyzedInstructionsRequest;
//import com.food.api.model.search.response.instruction.AnalyzedInstructionsResponse;
//import com.food.elasticsearch.model.findByIngredient.input.FindByIngredientsRequest;
//import com.food.elasticsearch.model.findbyingredient.output.FindByIngredientsResponse;
//import com.food.elasticsearch.services.RecipeService;
//import com.google.gson.GsonBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import java.util.List;
//
//
//@RestController
//@Path("/food_api/app")
//@Produces({"application/json"})
//@Consumes({"application/json"})
//@RequestMapping(value = "/food_api/app")
//@CrossOrigin
//public class RecipeImpl {
//
//    @Autowired
//    RecipeService recipeService;
//
//    @POST
//    @Path("/find_by_ingredient")
//    @Produces({"application/json"})
//    @Consumes({"application/json"})
//    @RequestMapping(value = "/find_by_ingredient", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<FindByIngredientsResponse> ingredientInquiry(
//            @Valid @RequestBody FindByIngredientsRequest request) throws Exception {
//        System.out.println("Initiating Recipes Inquiry\n");
//        System.out.println("Incoming Request:\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(request));
//
//        List<FindByIngredientsResponse> response = recipeService.findByIngredients(request);
//        System.out.println("\nOutgoing Response:\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(response));
//
////        FindByIngredientsResponse[] response = new GsonBuilder().create().fromJson(temporaryResponse(), FindByIngredientsResponse[].class);
////        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(response));
//
//        return new ResponseEntity(response, HttpStatus.OK);
//    }
//
//    @POST
//    @Path("/analyzed_instructions")
//    @Produces({"application/json"})
//    @Consumes({"application/json"})
//    @RequestMapping(value = "/analyzed_instructions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<AnalyzedInstructionsResponse> instructionsInquiry(
//            @Valid @RequestBody AnalyzedInstructionsRequest request) throws Exception {
//        System.out.println("Initiating Instructions Inquiry");
//        System.out.println("Incoming Request:\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(request));
//
//        List<AnalyzedInstructionsResponse> response = recipeService.analyzedInstructions(request);
//        System.out.println("\nOutgoing Response:\n");
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(response));
//
//        return new ResponseEntity(response, HttpStatus.OK);
//    }
//
//}
