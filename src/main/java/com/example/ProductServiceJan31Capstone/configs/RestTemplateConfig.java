package com.example.ProductServiceJan31Capstone.configs;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    // To be used for ProductDb service as we need load balanced our user service
    @Bean
    @LoadBalanced
    public RestTemplate getLoadBalancedRestTemplate(){
        return new RestTemplate();
    }

    // to be used for Third party services like fakeStore where we can't load balanced
    // fakeStore product APIs services.
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
