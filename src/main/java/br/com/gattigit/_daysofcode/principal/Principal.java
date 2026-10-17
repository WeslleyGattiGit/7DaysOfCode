package br.com.gattigit._daysofcode.principal;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import br.com.gattigit._daysofcode.models.DadosFilme;
import br.com.gattigit._daysofcode.services.FilmeService;
import br.com.gattigit._daysofcode.services.HTMLGenerator;
import br.com.gattigit._daysofcode.services.ListaService;

    public class Principal {

        private List<DadosFilme> listaFilmes = new ArrayList<>();
        
        @Bean
        public RestTemplate restTemplate(){
            return new RestTemplate();
        }

        public void run(){
            FilmeService filmeService = new FilmeService(restTemplate());
            listaFilmes = new ListaService().listar(filmeService);
            //System.out.println(listaFilmes);
            gerarHTML(listaFilmes);
        }

        private static void gerarHTML(List<DadosFilme> lista){
            try (Writer writer = new FileWriter("filmes.html")){
                writer.write(new HTMLGenerator().generate(lista));
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        }
    }
