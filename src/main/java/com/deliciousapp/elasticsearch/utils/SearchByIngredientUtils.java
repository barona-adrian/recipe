package com.deliciousapp.elasticsearch.utils;

import com.deliciousapp.onetime.findByID.model.output.AnalyzedInstructionsResponse;
import com.deliciousapp.elasticsearch.enums.ElasticMappingEnum;
import com.deliciousapp.elasticsearch.model.findbyingredient.input.Ingredient;
import com.deliciousapp.elasticsearch.model.findbyingredient.input.FindByIngredientsRequest;
import com.deliciousapp.elasticsearch.model.findbyingredient.output.FindByIngredientsOutput;
import com.deliciousapp.elasticsearch.model.findbyingredient.output.FindByElasticIngredientsResponse;
import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.searchbox.client.JestClient;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.search.builder.SearchSourceBuilder;
import io.searchbox.core.Search;
import org.springframework.web.context.annotation.RequestScope;

@Slf4j
@Component
@RequestScope
public class SearchByIngredientUtils {

    @Autowired
    private Utils utils;

    //Variables for Find By Ingredients API
    private List<String> ingredients;
    private List<Ingredient> ingredientList;
    private int itemsPerPage;

    private String number;
    private String ranking;
    private String fillIngredients;
    private String ingredientDelimiter;
    private String limitLicense;

    //Variables for Analyzed Instructions API
    private String recipeID;
    private boolean stepBreakdown;

    private JestClient jestClient;

    public SearchByIngredientUtils(@Autowired ElasticClient elasticClient){
        this.jestClient = elasticClient.getClient();
    }

    public List<FindByElasticIngredientsResponse> findByIngredients(FindByIngredientsRequest request) throws Exception {
        this.ingredients = request.getIngredients();
        this.ingredientList = request.getIngredientList();
        this.itemsPerPage = request.getItemsPerPage();

        this.number = request.getNumber();
        this.ranking = request.getRanking();
        this.fillIngredients = request.getFillIngredients();
        this.ingredientDelimiter = request.getIngredientDelimiter();
        this.limitLicense = request.getLimitLicense();
        return findByIngredient();
    }

    public AnalyzedInstructionsResponse analyzedInstructions(String id) throws Exception {

        this.recipeID = id;
        return analyzedInstruction();
    }

    private List<FindByElasticIngredientsResponse> findByIngredient() throws Exception {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(buildQuery());

        Search.Builder builder = new Search.Builder(searchSourceBuilder.toString()).addIndex("ingredients");

//        builder.setParameter("scroll", "scroll number here");
//        builder.setParameter("size", itemsPerPage);
//        builder.setParameter("from", "starting from what page? goes here");

        log.info("builder:\n" + searchSourceBuilder.toString());
        Search search = builder.build();

        SearchResult searchResult = jestClient.execute(search);

        log.info("Search Result:\n" + searchResult.getSourceAsString());

        return buildResponse(searchResult);
    }







    public BoolQueryBuilder buildQuery() throws NoSuchFieldException {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        processIngredients(boolQueryBuilder);


        return boolQueryBuilder;
    }

    private void processIngredients(BoolQueryBuilder boolQueryBuilder) throws NoSuchFieldException {

        if(ingredientList == null){
            return;
        }

        for(Ingredient ingredient : ingredientList) {
            for(String ing : ingredient.getName().split(" ")){
                if(ing.trim().length()>2){
                    addCondition(boolQueryBuilder, ing, utils.getElasticFieldMapping(Ingredient.class, "name"), ingredient.isMandatory());
                }

            }

        }

    }

    private void addCondition(BoolQueryBuilder boolQueryBuilder, String value, ElasticMappingEnum[] searchColumns, boolean mandatory) {

        String condition = mandatory ? "must" : "should";
        String[] searchCols = new String[searchColumns.length];
        for(int i = 0 ; i < searchCols.length ; i++){
            searchCols[i] = searchColumns[i].toString().replaceAll("_", ".");
        }
        log.info("Searching for '" + value + "' with condition '" + condition + ".");


        if(mandatory){
            boolQueryBuilder.must(QueryBuilders
                    .multiMatchQuery(value, searchCols)
                    .type(MultiMatchQueryBuilder.Type.MOST_FIELDS)
                    .operator(MatchQueryBuilder.Operator.OR)
                    .fuzziness("AUTO")
                    .queryName(value));
        }else {
            boolQueryBuilder.should(QueryBuilders
                    .multiMatchQuery(value, searchCols)
                    .type(MultiMatchQueryBuilder.Type.MOST_FIELDS)
                    .operator(MatchQueryBuilder.Operator.OR)
                    .fuzziness("AUTO")
                    .queryName(value));
        }
    }

    private AnalyzedInstructionsResponse analyzedInstruction() throws Exception {


        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermsQueryBuilder termQueryBuilder = QueryBuilders.termsQuery("_id", recipeID);
        searchSourceBuilder.query(termQueryBuilder);

        Search.Builder builder = new Search.Builder(searchSourceBuilder.toString()).addIndex("recipes");
        log.info("builder:\n" + searchSourceBuilder.toString());
        Search search = builder.build();
        SearchResult searchResult = jestClient.execute(search);
        log.info("Search Result:\n" + searchResult.getSourceAsString());

        AnalyzedInstructionsResponse response = new Gson().fromJson(searchResult.getSourceAsString(), AnalyzedInstructionsResponse.class);

        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(response));


        return response;
    }


    private List<FindByElasticIngredientsResponse> buildResponse(SearchResult searchResult) {
        if (searchResult == null){
            log.info("No Results!");
            return null;
        }
        List<FindByElasticIngredientsResponse> responses = Arrays.asList(new Gson().fromJson(searchResult.getSourceAsStringList().toString(), FindByElasticIngredientsResponse[].class));

        for(int i = 0 ; i < responses.size() ; i++){
            JsonArray j = searchResult
                    .getJsonObject()
                    .getAsJsonObject("hits")
                    .getAsJsonArray("hits")
                    .get(i).getAsJsonObject()
                    .getAsJsonArray("matched_queries");
            List<String> matchedIngs = new ArrayList<>();
            for(JsonElement jj : j){
                System.out.println(jj);
                matchedIngs.add(jj.getAsString());
            }
            responses.get(i).setMatchedIngredients(matchedIngs);
        }
        return responses;
    }

}
