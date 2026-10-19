package br.com.gattigit._daysofcode.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gattigit._daysofcode.models.DadosFilme;
import br.com.gattigit._daysofcode.services.FavoritosService;
import br.com.gattigit._daysofcode.services.FilmeService;
import br.com.gattigit._daysofcode.services.HTMLGenerator;
import br.com.gattigit._daysofcode.services.ListaService;

@RestController 
public class FilmeController {
    
    private final FilmeService filmeService;
    
    private final ListaService listaService;

    private final FavoritosService favoritosService;

    public FilmeController(FilmeService filmeService, ListaService listaService, FavoritosService favoritosService){
        this.filmeService = filmeService;
        this.listaService = listaService;
        this.favoritosService = favoritosService;
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

    @GetMapping(value = "/favoritos", produces = "text/html")
    public String generateHTMLFavoritos(){
        return new HTMLGenerator().generate(favoritosService.getFavoritos().stream()
                .map(f -> f.getDadosFilme())
                .toList());
    }

    @PostMapping(value="/favoritos/{id}")
    public void AddFavoritos(@PathVariable Integer id){
        favoritosService.addFavoritos(id);
        // 
    }
}
