package com.example.demo;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Delete;
import org.springframework.beans.factory.annotation.Autowired;

public class ElasticConfig {

    public JestClient jestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(
                new HttpClientConfig.Builder("http://localhost:9200")
                        .multiThreaded(true)
                        .defaultMaxTotalConnectionPerRoute(2)
                        .maxTotalConnection(10)
                        .build());
        return factory.getObject();
    }
}
