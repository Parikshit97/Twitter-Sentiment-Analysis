package com.example.demo;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HelloWorld {

//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        return template;
//    }

//    @RequestMapping("/helloworld")
//    public String index() throws IOException {
//
//        ElasticConfig elasticConfig = new ElasticConfig();
//        JestClient jestClient = elasticConfig.jestClient();
//        Hotels hotel = jestClient.execute(new Get.Builder("db_ranking", "xdNaOnEBC_varp_MSb4x").build()).getSourceAsObject(Hotels.class);
//        System.out.println(hotel);
//        return "hello_world";
//    }
}
