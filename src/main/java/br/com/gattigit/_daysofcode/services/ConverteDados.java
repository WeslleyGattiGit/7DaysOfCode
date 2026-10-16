package br.com.gattigit._daysofcode.services;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

public class ConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T Conversor(String json, Class<T> classe){
        try{
            return mapper.readValue(json, classe);
        }catch(JacksonException e){
            throw new RuntimeException(e);
        }
    }
}
