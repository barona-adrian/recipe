package com.deliciousapp.onetime.elasticupdate;

import com.deliciousapp.onetime.findByID.model.output.AnalyzedInstructionsResponse;
import com.deliciousapp.elasticsearch.model.findbyingredient.output.FindByElasticIngredientsResponse;
import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ElasticUpdate {

    private JestClient client;

    public void jestSetup(){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(
                new HttpClientConfig.Builder("https://search-deliciousapp-z3bbczxj7yuvplnvdxmfz6fbtq.us-east-2.es.amazonaws.com")
                        .defaultCredentials("admin", "password")
                        .multiThreaded(true).maxConnectionIdleTime(2, TimeUnit.DAYS).connTimeout(1000000).readTimeout(1000000)
                        .build()
        );
        client = factory.getObject();
    }

    public void updateElasticIngredients(FindByElasticIngredientsResponse elasticInput) throws IOException {


        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(elasticInput));


        String source = new GsonBuilder().setPrettyPrinting().create().toJson(elasticInput);
        String id = elasticInput.getId();
        JestResult result = client.execute(
                new Index.Builder(source)
                        .index("test")
                        .type("default")
                        .id(id)
                        .build()
        );

        System.out.println(result.getSourceAsString());

    }

    public void updateElasticRecipes(AnalyzedInstructionsResponse elasticInput, String id) throws IOException {
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(elasticInput));

        String source = new GsonBuilder().setPrettyPrinting().create().toJson(elasticInput);
        JestResult result = client.execute(
                new Index.Builder(source)
                        .index("test")
                        .type("default")
                        .id(id)
                        .build()
        );

        System.out.println(result.getSourceAsString());

    }

}
