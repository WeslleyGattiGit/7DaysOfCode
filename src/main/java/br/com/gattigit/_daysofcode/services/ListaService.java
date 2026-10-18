package br.com.gattigit._daysofcode.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gattigit._daysofcode.Application;
import br.com.gattigit._daysofcode.models.DadosFilme;
import br.com.gattigit._daysofcode.models.DadosLista;
import br.com.gattigit._daysofcode.models.Filme;

@Service 
public class ListaService {
    
    private List<Filme> listaFilmes = new ArrayList<>();

    public List<DadosFilme> listar(FilmeService fService, String title){
        //vars
        String json;
        ConverteDados conversor = new ConverteDados();
        
        if(listaFilmes.isEmpty()){
            int contador =0;
            for(int i = 1; i<=13; i++){
                json = fService.buscarFilmes(Application.URL+i);
                DadosLista dadosFilmes = conversor.Conversor(json, DadosLista.class);
                //puxa os 260
                for(DadosFilme filme : dadosFilmes.listaFilmes()){
                    contador++;
                    listaFilmes.add(new Filme(contador, filme));
                    //separa so 250
                    if (contador>=250) break;
                }
            }
        }
        /*filtra baseado no titulo   */
        List<DadosFilme> listaFiltrada = listaFilmes.stream()
                .filter(f -> f.getDadosFilme().titulo().toLowerCase().contains(
                    title.toLowerCase()
                )).map(Filme::getDadosFilme)
                .toList();
        // listaFilmes.stream()
        //         .filter(f -> f.titulo().toLowerCase().contains(
        //             title.toLowerCase()))
        //         .toList();
        if(!title.isEmpty()) return listaFiltrada;
        return listaFilmes.stream().map(Filme::getDadosFilme).toList();
    }

    /*Sobrecarga */
    public List<DadosFilme> listar(FilmeService fService){
        return listar(fService, "");
    }
}
