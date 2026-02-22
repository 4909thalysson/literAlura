package br.com.challenge.literalura.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "livro")
public class Livros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String idioma;
    private Double numeroDownloads;

    private LocalDate nasc;  // ano de nascimento
    private LocalDate morte; // ano de morte

    public Livros() {}

    public Livros(String titulo, String autor, String idioma, Double numeroDownloads) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDownloads = numeroDownloads;
    }

    public Long getId() { return id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public Double getNumeroDownloads() { return numeroDownloads; }
    public void setNumeroDownloads(Double numeroDownloads) { this.numeroDownloads = numeroDownloads; }

    public LocalDate getNasc() { return nasc; }
    public void setNasc(LocalDate nasc) { this.nasc = nasc; }

    public LocalDate getMorte() { return morte; }
    public void setMorte(LocalDate morte) { this.morte = morte; }
}