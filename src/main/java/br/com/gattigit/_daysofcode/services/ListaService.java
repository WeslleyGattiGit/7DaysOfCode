package br.com.gattigit._daysofcode.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gattigit._daysofcode.Application;
import br.com.gattigit._daysofcode.models.DadosFilme;
import br.com.gattigit._daysofcode.models.DadosLista;

@Service 
public class ListaService {
    public List<DadosFilme> listar(FilmeService fService){
        //vars
        String json;
        ConverteDados conversor = new ConverteDados();
        List<DadosFilme> listaFilmes = new ArrayList<>();
        

        int contador =0;
        for(int i = 1; i<=13; i++){
            json = fService.buscarFilmes(Application.URL+i);
            DadosLista dadosFilmes = conversor.Conversor(json, DadosLista.class);
            //puxa os 260
            for(DadosFilme filme : dadosFilmes.listaFilmes()){
                contador++;
                listaFilmes.add(filme);
                //separa so 250
                if (contador>=250) break;
            }
        }

        return listaFilmes;
    }
}
