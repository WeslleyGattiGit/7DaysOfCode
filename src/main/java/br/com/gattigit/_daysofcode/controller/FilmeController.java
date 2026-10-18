package br.com.gattigit._daysofcode.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gattigit._daysofcode.models.DadosFilme;
import br.com.gattigit._daysofcode.services.FilmeService;
import br.com.gattigit._daysofcode.services.HTMLGenerator;
import br.com.gattigit._daysofcode.services.ListaService;

@RestController 
public class FilmeController {
    
    private final FilmeService filmeService;
    
    private final ListaService listaService;

    public FilmeController(FilmeService filmeService, ListaService listaService){
        this.filmeService = filmeService;
        this.listaService = listaService;
    }

    @GetMapping("/top250")
    public List<DadosFilme> buscarFilmes(){
        return listaService.listar(filmeService);
    }

    @GetMapping(value = "/gen-cards", produces = "text/html")
    public String generateHTML(@RequestParam(defaultValue="") String title){
        if(title.isEmpty())
            return new HTMLGenerator().generate(listaService.listar(filmeService));
        return new HTMLGenerator().generate(listaService.listar(filmeService, title));
    }
}
