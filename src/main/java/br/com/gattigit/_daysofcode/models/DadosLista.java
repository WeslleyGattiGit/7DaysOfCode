package br.com.gattigit._daysofcode.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosLista(@JsonAlias ("results") List<DadosFilme> listaFilmes) {
}