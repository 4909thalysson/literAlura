package br.com.challenge.literalura.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate nasc;   // ano de nascimento
    private LocalDate morte;  // ano de morte

    public Autor() {}  // construtor vazio obrigatório pelo JPA

    public Autor(String nome, LocalDate nasc, LocalDate morte) {
        this.nome = nome;
        this.nasc = nasc;
        this.morte = morte;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNasc() {
        return nasc;
    }

    public void setNasc(LocalDate nasc) {
        this.nasc = nasc;
    }

    public LocalDate getMorte() {
        return morte;
    }

    public void setMorte(LocalDate morte) {
        this.morte = morte;
    }
}
