package br.com.gattigit._daysofcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gattigit._daysofcode.Application;
import br.com.gattigit._daysofcode.services.FilmeService;

@RestController 
public class FilmeController {
    
    private FilmeService filmeService;

    public FilmeController(FilmeService filmeService){
        this.filmeService = filmeService;
    }

    @GetMapping("/top250")
    public String buscarFilmes(){
        return filmeService.buscarFilmes(Application.URL);
    } 

}
