package br.com.challenge.literalura.service;

import br.com.challenge.literalura.dto.LivroDto;
import br.com.challenge.literalura.model.Livros;
import br.com.challenge.literalura.repository.LivrosRepository;
import br.com.challenge.literalura.util.JsonConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Service
public class LivroService {

    private final ConsumoApiService consumoApiService;
    private final LivrosRepository repository;

    public LivroService(ConsumoApiService consumoApiService,
                        LivrosRepository repository) {
        this.consumoApiService = consumoApiService;
        this.repository = repository;
    }


//    @Transactional
//    public boolean buscarESalvarLivroPorId(Long id) {
//
//        String json = consumoApiService.buscarLivroPorId(id);
//
//        LivroDto dto = JsonConverter.fromJson(json, LivroDto.class);
//
//        if (dto == null) {
//            return false;
//        }
//
//        if (repository.existsByTitulo(dto.title())) {
//            return false;
//        }
//
//        Livros livro = converterDtoParaEntidade(dto);
//
//        repository.save(livro);
//
//        return true;
//    }


    @Transactional
    public List<Livros> buscarESalvarLivrosPorTitulo(String titulo) {
        List<LivroDto> livrosDto = consumoApiService.buscarLivro(titulo);
        if (livrosDto.isEmpty()) return List.of();

        // Dividir o título em palavras e filtrar os livros que contenham todas elas
        String[] palavras = titulo.toLowerCase().split("\\s+");
        List<LivroDto> filtrados = livrosDto.stream()
                .filter(dto -> {
                    String t = dto.title().toLowerCase();
                    for (String p : palavras) {
                        if (!t.contains(p)) return false;
                    }
                    return true;
                })
                .toList();

        List<Livros> livrosSalvos = new ArrayList<>();
        for (LivroDto dto : filtrados) {
            if (!repository.existsByTitulo(dto.title())) {
                Livros livro = converterDtoParaEntidade(dto);
                repository.save(livro);
                livrosSalvos.add(livro);
            }
        }

        return livrosSalvos;
    }

    public List<Livros> listarTodos() {
        return repository.findAll();
    }


    public List<Livros> buscarPorTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCase(titulo);
    }


    public List<Livros> buscarPorAutor(String autor) {
        return repository.findByAutorContainingIgnoreCase(autor);
    }


    private Livros converterDtoParaEntidade(LivroDto dto) {
        Livros livro = new Livros();

        livro.setTitulo(dto.title());

        livro.setIdioma(dto.languages() != null && !dto.languages().isEmpty()
                ? dto.languages().get(0)
                : "Desconhecido");

        if (dto.authors() != null && !dto.authors().isEmpty()) {
            var autorDto = dto.authors().get(0);
            livro.setAutor(autorDto.name());

            // Conversão Integer -> LocalDate
            if (autorDto.birthYear() != null) {
                livro.setNasc(LocalDate.of(autorDto.birthYear(), 1, 1));
            }
            if (autorDto.deathYear() != null) {
                livro.setMorte(LocalDate.of(autorDto.deathYear(), 1, 1));
            }
        } else {
            livro.setAutor("Desconhecido");
            livro.setNasc(null);
            livro.setMorte(null);
        }

        livro.setNumeroDownloads(dto.downloads() != null
                ? dto.downloads().doubleValue()
                : 0.0);

        return livro;
    }
}
