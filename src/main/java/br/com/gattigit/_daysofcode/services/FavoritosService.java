package br.com.gattigit._daysofcode.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gattigit._daysofcode.models.Filme;

@Service 
public class FavoritosService {
    List<Filme> favoritos = new ArrayList<>();

    private final ListaService listaService;

    public FavoritosService(ListaService listaService){
        this.listaService = listaService;
    }

    public void addFavoritos(Integer id){
        listaService.getListaFilmes().stream()
            .filter(f -> f.getId().equals(id))
            .filter(f -> favoritos.stream()
                .noneMatch(fav -> fav.getId().equals(id)))
            .findFirst()
            .ifPresent(favoritos::add);

        favoritos.forEach(f -> System.out.println(f.getId()));
    }

    public List<Filme> getFavoritos() {
        return favoritos;
    }

    

}
