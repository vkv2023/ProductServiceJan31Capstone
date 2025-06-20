package com.example.ProductServiceJan31Capstone.commons;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApplicationCommons {

    RestTemplate restTemplate;
    public ApplicationCommons(@Qualifier("getLoadBalancedRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void validateToken(String token){
        if (token == null || token.isEmpty()){
            throw new RuntimeException("Invalid Token : Empty Token");
        }

        // To validate the token using UserServiceJan31Capstone-master and below API (use userapi port)
         String url = "http://localhost:9001/validate/" + token ;

        // use below to direct the request to be handled by any instance of service
        // String url = "http://UserServiceJan31Capstone-master/validate/" + token ;
        // restTemplate second argument as return type from the API.
        Boolean isValidToken = restTemplate.getForObject(url,Boolean.class);

        if (Boolean.FALSE.equals(isValidToken)){
            throw new RuntimeException("Token is not valid.");
        }

    }
}
