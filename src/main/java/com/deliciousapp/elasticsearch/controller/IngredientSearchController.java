package com.deliciousapp.elasticsearch.controller;

import com.deliciousapp.elasticsearch.model.findbyingredient.output.FindByElasticIngredientsResponse;
import com.deliciousapp.onetime.findByID.model.output.AnalyzedInstructionsResponse;
//import com.food.elasticsearch.OneTimeLoad;
import com.deliciousapp.elasticsearch.model.findbyingredient.input.FindByIngredientsRequest;
import com.deliciousapp.elasticsearch.model.findbyingredient.output.FindByIngredientsOutput;
import com.deliciousapp.elasticsearch.services.RecipeService;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.*;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@Path("/elasticsearch/ingredients")
@Produces({"application/json"})
@Consumes({"application/json"})
@RequestMapping(value = "/elasticsearch/ingredients")
@CrossOrigin
public class IngredientSearchController {

    @Autowired
    RecipeService recipeService;

    @POST
    @Path("/find_by_ingredient")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    @RequestMapping(value = "/find_by_ingredient", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FindByElasticIngredientsResponse> ingredientInquiry(
            @Valid @RequestBody FindByIngredientsRequest request) throws Exception {

        long startTimeMillis = System.currentTimeMillis();

        LocalDateTime dateTime = LocalDateTime.now();

        log.info("Initiating Recipes Inquiry");
        log.info("Start Timestamp: " + dateTime);
        log.info("Incoming Request:\n" + new GsonBuilder().setPrettyPrinting().create().toJson(request));

        List<FindByElasticIngredientsResponse> response = recipeService.findByIngredients(request);
        log.info("\nOutgoing Response:\n");
        log.info(new GsonBuilder().setPrettyPrinting().create().toJson(response));

//        FindByIngredientsResponse[] response = new GsonBuilder().create().fromJson(temporaryResponse(), FindByIngredientsResponse[].class);
//        log.info(new GsonBuilder().setPrettyPrinting().create().toJson(response));

        long endTimeMillis = System.currentTimeMillis();

        log.info("Total Execution time: " + (endTimeMillis - startTimeMillis));

        return new ResponseEntity(response, HttpStatus.OK);
    }



    @GET
    @Path("/get_recipe")
    @Produces({"application/json"})
    @RequestMapping(value = "/get_recipe", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnalyzedInstructionsResponse> instructionsInquiry(
            @HeaderParam("id") @RequestParam String id) throws Exception {
        long startTimeMillis = System.currentTimeMillis();
        System.out.println("Initiating Instructions Inquiry");
        System.out.println("Incoming Request:\n");

        System.out.println("id = " + id);

        AnalyzedInstructionsResponse response = recipeService.analyzedInstructions(id);
        System.out.println("\nOutgoing Response:\n");
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(response));
        long endTimeMillis = System.currentTimeMillis();

        log.info("Total Execution time: " + (endTimeMillis - startTimeMillis));

//        return null;
        return new ResponseEntity(response, HttpStatus.OK);
    }

    
}
