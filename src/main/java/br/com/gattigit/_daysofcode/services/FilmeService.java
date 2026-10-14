package br.com.gattigit._daysofcode.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.cdimascio.dotenv.Dotenv;

@Service 
public class FilmeService {
    Dotenv dotenv = Dotenv.load();

    private final RestTemplate restTemplate;

    public FilmeService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String buscarFilmes(String url){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(dotenv.get("READ_ACESS_TOKEN"));

        //requisicao utilizando meu token
        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            request,
            String.class
        );

        return response.getBody();
    }
}
