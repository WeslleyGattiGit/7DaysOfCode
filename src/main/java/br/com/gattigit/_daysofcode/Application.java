package br.com.gattigit._daysofcode;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import br.com.gattigit._daysofcode.services.FilmeService;

@SpringBootApplication
public class Application implements CommandLineRunner{
	private String url = "https://api.themoviedb.org/3/movie/top_rated?language=pt-BR&page=1";

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void run(String... args){
		FilmeService filmeService = new FilmeService(restTemplate());
		System.out.println(filmeService.buscarFilmes(url));
	}
}
