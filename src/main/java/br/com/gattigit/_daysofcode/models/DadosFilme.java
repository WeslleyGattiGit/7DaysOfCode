package br.com.gattigit._daysofcode.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)
public record DadosFilme (@JsonAlias ("title")String titulo//string
                            ,@JsonAlias ("poster_path")String poster//string
                            ,@JsonAlias ("vote_average")Double nota//double
                            ,@JsonAlias ("release_date") String lancamento){//string
}
