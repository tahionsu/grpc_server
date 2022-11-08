package org.example.template;

import org.example.entity.CoffeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class CoffeeClient {

    private RestTemplate restTemplate;

    public CoffeeClient(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CoffeeEntity getCoffee(int id, String url) {
        try
        {
            ResponseEntity<CoffeeEntity> coffee = restTemplate
                    .getForEntity(url + "/?id=" + id, CoffeeEntity.class);
            return coffee.getBody();
        } catch (HttpClientErrorException clientError) {
            System.out.println(clientError.getMessage());
            return null;
        }
    }

    public String postCoffee(CoffeeEntity coffee, String url) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(coffee.toString(), headers);

        return restTemplate.postForObject(url, request, String.class);
    }

    public int deleteCoffee(int id, String url) {
        String urlAll = url + '/' + id;
        restTemplate.delete(urlAll);
        return 0;
    }

}
