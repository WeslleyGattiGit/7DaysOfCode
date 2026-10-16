    package br.com.gattigit._daysofcode.principal;

    import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import br.com.gattigit._daysofcode.Application;
import br.com.gattigit._daysofcode.models.DadosFilme;
import br.com.gattigit._daysofcode.models.DadosLista;
import br.com.gattigit._daysofcode.services.ConverteDados;
import br.com.gattigit._daysofcode.services.FilmeService;

    public class Principal {

        private String json;

        private ConverteDados conversor = new ConverteDados();
        
        @Bean
        public RestTemplate restTemplate(){
            return new RestTemplate();
        }

        public void run(){
            FilmeService filmeService = new FilmeService(restTemplate());
            json = filmeService.buscarFilmes(Application.URL);
            DadosLista dadosFilmes = conversor.Conversor(json, DadosLista.class);
            List<DadosFilme> listaFilmes = dadosFilmes.listaFilmes();
            System.out.println(listaFilmes);
            // System.out.println(json);
        }
    }
