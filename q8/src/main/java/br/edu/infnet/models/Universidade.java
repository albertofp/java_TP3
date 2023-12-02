package br.edu.infnet.models;

public class Universidade {
    private String nome;
    private String url;

    // Constructor
    public Universidade(String nome, String url) {
        this.nome = nome;
        this.url = url;
    }

    // Getters and setters (or public fields)

    @Override
    public String toString() {
        return "{" +
                "nome='" + nome + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
