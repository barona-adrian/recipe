//package com.deliciousapp.elasticsearch.utils;
//
//import com.deliciousapp.elasticsearch.enums.ElasticMappingEnum;
//import com.deliciousapp.elasticsearch.model.findbyingredient.input.FindByIngredientsRequest;
//import com.deliciousapp.elasticsearch.model.findbyingredient.input.Ingredient;
//import com.deliciousapp.elasticsearch.model.findbyingredient.output.FindByElasticIngredientsResponse;
//import com.deliciousapp.onetime.findByID.model.output.AnalyzedInstructionsResponse;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import io.searchbox.client.JestClient;
//import io.searchbox.core.Search;
//import io.searchbox.core.SearchResult;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.index.query.*;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.annotation.RequestScope;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Slf4j
//@Component
//@RequestScope
//public class SearchByIDUtils {
//
//    //Variables for Analyzed Instructions API
//    private String recipeID;
//
//    private JestClient jestClient;
//
//    public SearchByIDUtils(@Autowired ElasticClient elasticClient){
//        this.jestClient = elasticClient.getClient();
//    }
//
//    public AnalyzedInstructionsResponse analyzedInstructions(String id) throws Exception {
//        this.recipeID = id;
//        return analyzedInstruction();
//    }
//
//
//
//
//
//
//
//    public BoolQueryBuilder buildQuery() throws NoSuchFieldException {
//
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        processIngredients(boolQueryBuilder);
//
//
//        return boolQueryBuilder;
//    }
//
//    private void processIngredients(BoolQueryBuilder boolQueryBuilder) throws NoSuchFieldException {
//
//        if(ingredientList == null){
//            return;
//        }
//
//        for(Ingredient ingredient : ingredientList) {
//            for(String ing : ingredient.getName().split(" ")){
//                if(ing.trim().length()>2){
//                    addCondition(boolQueryBuilder, ing, utils.getElasticFieldMapping(Ingredient.class, "name"), ingredient.isMandatory());
//                }
//
//            }
//
//        }
//
//    }
//
//    private void addCondition(BoolQueryBuilder boolQueryBuilder, String value, ElasticMappingEnum[] searchColumns, boolean mandatory) {
//
//        String condition = mandatory ? "must" : "should";
//        String[] searchCols = new String[searchColumns.length];
//        for(int i = 0 ; i < searchCols.length ; i++){
//            searchCols[i] = searchColumns[i].toString().replaceAll("_", ".");
//        }
//        log.info("Searching for '" + value + "' with condition '" + condition + ".");
//
//
//        if(mandatory){
//            boolQueryBuilder.must(QueryBuilders
//                    .multiMatchQuery(value, searchCols)
//                    .type(MultiMatchQueryBuilder.Type.MOST_FIELDS)
//                    .operator(MatchQueryBuilder.Operator.OR)
//                    .fuzziness("AUTO")
//                    .queryName(value));
//        }else {
//            boolQueryBuilder.should(QueryBuilders
//                    .multiMatchQuery(value, searchCols)
//                    .type(MultiMatchQueryBuilder.Type.MOST_FIELDS)
//                    .operator(MatchQueryBuilder.Operator.OR)
//                    .fuzziness("AUTO")
//                    .queryName(value));
//        }
//    }
//
//    private AnalyzedInstructionsResponse analyzedInstruction() throws Exception {
//
//
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        TermsQueryBuilder termQueryBuilder = QueryBuilders.termsQuery("_id", recipeID);
//        searchSourceBuilder.query(termQueryBuilder);
//
//        Search.Builder builder = new Search.Builder(searchSourceBuilder.toString()).addIndex("recipes");
//        log.info("builder:\n" + searchSourceBuilder.toString());
//        Search search = builder.build();
//        SearchResult searchResult = jestClient.execute(search);
//        log.info("Search Result:\n" + searchResult.getSourceAsString());
//
//        AnalyzedInstructionsResponse response = new Gson().fromJson(searchResult.getSourceAsString(), AnalyzedInstructionsResponse.class);
//
//        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(response));
//
//
//        return response;
//    }
//
//}
