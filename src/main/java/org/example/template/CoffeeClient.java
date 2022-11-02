package org.example.template;

import org.example.entity.CoffeeEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class CoffeeClient {

    public static CoffeeEntity getCoffee(int id, String url) {
        try
        {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<CoffeeEntity> coffee = restTemplate
                    .getForEntity(url + "/?id=" + id, CoffeeEntity.class);
            return coffee.getBody();
        } catch (HttpClientErrorException clientError) {
            System.out.println(clientError.getMessage());
            return null;
        }
    }

    public static String postCoffee(CoffeeEntity coffee, String url) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(coffee.toString(), headers);

        return restTemplate.postForObject(url, request, String.class);
    }

    public static int deleteCoffee(int id, String url) {
        RestTemplate restTemplate = new RestTemplate();
        String urlAll = url + '/' + id;
        restTemplate.delete(urlAll);
        return 0;
    }

}
