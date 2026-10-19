package br.com.gattigit._daysofcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.gattigit._daysofcode.services.FavoritosService;
import br.com.gattigit._daysofcode.services.FilmeService;
import br.com.gattigit._daysofcode.services.ListaService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@TestConfiguration
    static class TestConfig {

        @Bean
        TestRestTemplate testRestTemplate() {
            return new TestRestTemplate();
        }
    }

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired 
	private FavoritosService favoritosService;
	
	@Autowired 
	private FilmeService filmeService;

	@Autowired 
	private ListaService listaService;

	@Test
	void shouldReturnTop250Films() {
		ResponseEntity<String> response = restTemplate.getForEntity(
			"http://localhost:" + port + "/top250",
            String.class
		);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
	}

	@Test
	void shouldReturnCardsGeneration() {
		ResponseEntity<String> response = restTemplate.getForEntity(
			"http://localhost:" + port + "/gen-cards",
            String.class
		);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertTrue(response.getHeaders().getContentType()
			.isCompatibleWith(MediaType.TEXT_HTML));
		}
		
	
	@Test
	void shouldAddFavoritos(){
		listaService.listar(filmeService);

		ResponseEntity<String> response = restTemplate.postForEntity(
			"http://localhost:" + port + "/favoritos/1"
			,null
			,String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(favoritosService.getFavoritos().stream()
				.anyMatch(f -> f.getId().equals(1)));
	}

	@Test
	void shouldReturnFavoritos(){
		listaService.listar(filmeService);
		restTemplate.postForEntity(
			"http://localhost:" + port + "/favoritos/1"
			,null
			,String.class);

		ResponseEntity<String> response = restTemplate.getForEntity(
			"http://localhost:" + port + "/favoritos"
			,String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertTrue(response.getHeaders().getContentType()
			.isCompatibleWith(MediaType.TEXT_HTML));
	}
}
