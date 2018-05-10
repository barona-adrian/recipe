package com.deliciousapp.elasticsearch.utils;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Component
@lombok.Getter
public class ElasticClient {

    private JestClient client;

    public ElasticClient(@Value("${elasticsearch.server.url}") String elasticServerURL){

        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(
                new HttpClientConfig.Builder(elasticServerURL).defaultCredentials("admin", "password")
                .multiThreaded(true).maxConnectionIdleTime(2, TimeUnit.DAYS).connTimeout(1000000).readTimeout(1000000)
                .build()
        );

//        JestClient j = factory.getObject();

//        client = j;

    }

    @PreDestroy
    public void shutDownClient(){
        if(client != null){
            client.shutdownClient();
        }
    }

}
