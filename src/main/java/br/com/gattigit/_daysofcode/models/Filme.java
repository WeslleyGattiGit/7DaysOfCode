package br.com.gattigit._daysofcode.models;

import java.time.LocalDate;

public class Filme {
    private String titulo;
    private String poster;
    private Double nota;
    private Integer ano;

    public Filme(String titulo, String poster, Double nota, String ano){
        this.titulo = titulo;
        this.poster = poster;
        this.nota = nota;
        this.ano = LocalDate.parse(ano).getYear();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "titulo=" + titulo + ", poster=" + poster + ", nota=" + nota + ", ano=" + ano;
    }

    
}
