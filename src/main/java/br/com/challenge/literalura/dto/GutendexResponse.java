package br.com.challenge.literalura.dto;

import java.util.List;

public class GutendexResponse {

    private List<LivroDto> results;

    public List<LivroDto> getResults() {
        return results;
    }

    public void setResults(List<LivroDto> results) {
        this.results = results;
    }
}