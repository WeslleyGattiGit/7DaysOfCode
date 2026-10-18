package br.com.gattigit._daysofcode.models;

public class Filme {
    
    private Integer id;

    private DadosFilme dadosFilme;

    public Filme(Integer id, DadosFilme dadosFilme){
        this.id = id;
        this.dadosFilme = dadosFilme;
    }

    public Integer getId() {
        return id;
    }

    public DadosFilme getDadosFilme() {
        return dadosFilme;
    }
}
