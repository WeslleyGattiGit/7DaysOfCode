package br.com.gattigit._daysofcode.services;

import java.time.LocalDate;
import java.util.List;

import br.com.gattigit._daysofcode.models.DadosFilme;

public class HTMLGenerator {

    public String generate(List<DadosFilme> filmes){
        String texto ="";
        texto += """
                <!DOCTYPE html>
                <html lang="pt-BR">
                    <head>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
                        <meta charset="UTF-8">
                        <title>Filmes</title>
                    </head>
                    <body>
                        <h1>Meus Filmes</h1>
                        <div class="container mt-4">
                        <div class="row row-col-1 row-col-sm-2 row-col-md-3 row-col-lg-4 g-4">
                """;
                for(DadosFilme filme : filmes){
                texto +="""
                            <div class="col">
                                <div class="card h-100">
                                    <img src="https://image.tmdb.org/t/p/w500%s" 
                                    class="card-img-top" 
                                    alt="Pôster de %s">

                                    <div class="card-body">
                                        <h5 class="card-title">%s</h2>
                                        <p class="card-text">Nota: %.1f</p>
                                        <p class="card-text">Lançamento: %d</p>
                                    </div>
                                </div>
                            </div>
                    """.formatted(
                        filme.poster(),
                        filme.titulo(),
                        filme.titulo(),
                        filme.nota(),
                        LocalDate.parse(filme.lancamento()).getYear()
                    );
            }
            texto += """
                        </div>
                        </div>
                    </body>
                </html>
            """;
        return texto;
    }
}
