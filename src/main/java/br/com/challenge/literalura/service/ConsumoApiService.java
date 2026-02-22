package br.com.challenge.literalura.service;

import br.com.challenge.literalura.dto.AutorDto;
import br.com.challenge.literalura.dto.GutendexResponse;
import br.com.challenge.literalura.dto.LivroDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ConsumoApiService {
    private static final String BASE_URL = "https://gutendex.com/books/";

    private final RestTemplate restTemplate = new RestTemplate();

    public String buscarLivroPorId(Long id) {
        return restTemplate.getForObject(BASE_URL + id, String.class);
    }

    public List<LivroDto> buscarLivro(String titulo) {

        String url = UriComponentsBuilder
                .fromHttpUrl(BASE_URL)
                .queryParam("search", titulo)
                .toUriString();

        GutendexResponse response =
                restTemplate.getForObject(url, GutendexResponse.class);

        return response != null ? response.getResults() : List.of();
    }

    public List<AutorDto> buscarAutoresNaApi() {
        // Busca todos os livros (ou usa um trecho vazio para pegar tudo)
        List<LivroDto> livros = buscarLivro("");

        // Extrai os autores únicos de todos os livros
        return livros.stream()
                .flatMap(l -> l.authors().stream())
                .distinct() // remove duplicados
                .toList();
    }
}
