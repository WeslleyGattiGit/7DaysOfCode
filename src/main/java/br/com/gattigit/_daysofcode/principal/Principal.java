package br.com.gattigit._daysofcode.principal;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import br.com.gattigit._daysofcode.Application;
import br.com.gattigit._daysofcode.models.DadosFilme;
import br.com.gattigit._daysofcode.models.DadosLista;
import br.com.gattigit._daysofcode.services.ConverteDados;
import br.com.gattigit._daysofcode.services.FilmeService;
import br.com.gattigit._daysofcode.services.HTMLGenerator;

    public class Principal {

        private String json;

        private ConverteDados conversor = new ConverteDados();

        private List<DadosFilme> listaFilmes = new ArrayList<>();
        
        private int contador =0;
        
        @Bean
        public RestTemplate restTemplate(){
            return new RestTemplate();
        }

        public void run(){
            FilmeService filmeService = new FilmeService(restTemplate());
            for(int i = 1; i<=13; i++){
                json = filmeService.buscarFilmes(Application.URL+i);
                DadosLista dadosFilmes = conversor.Conversor(json, DadosLista.class);
                //puxa os 260
                for(DadosFilme filme : dadosFilmes.listaFilmes()){
                    contador++;
                    listaFilmes.add(filme);
                    if (contador>=250) break;
                }
            }
            //System.out.println(listaFilmes);
            gerarHTML(listaFilmes);
        }

        private static void gerarHTML(List<DadosFilme> lista){
            try (Writer writer = new FileWriter("filmes.html")){
                HTMLGenerator gerador = new HTMLGenerator(writer);
                gerador.generate(lista);
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        }
    }
