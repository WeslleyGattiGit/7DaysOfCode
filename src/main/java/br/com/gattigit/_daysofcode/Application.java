package br.com.gattigit._daysofcode;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import br.com.gattigit._daysofcode.principal.Principal;

@SpringBootApplication
public class Application implements CommandLineRunner{
	public static final String URL = "https://api.themoviedb.org/3/movie/top_rated?language=pt-BR&page=1";

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void run(String... args){
		new Principal().run();
	}
}
