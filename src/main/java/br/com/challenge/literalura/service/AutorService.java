package br.com.challenge.literalura.service;

import br.com.challenge.literalura.model.Autor;
import br.com.challenge.literalura.repository.AutorRepository;
import br.com.challenge.literalura.dto.AutorDto;
import br.com.challenge.literalura.service.ConsumoApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    private final ConsumoApiService consumoApiService;
    private final String perfilAtivo;

    public AutorService(AutorRepository autorRepository,
                        ConsumoApiService consumoApiService,
                        @Value("${spring.profiles.active}") String perfilAtivo) {
        this.autorRepository = autorRepository;
        this.consumoApiService = consumoApiService;
        this.perfilAtivo = perfilAtivo;
    }

    public void buscaAutores() {
        List<Autor> autores;

        if ("dev".equalsIgnoreCase(perfilAtivo)) {
            autores = consumoApiService.buscarAutoresNaApi()
                    .stream()
                    .map(dto -> {
                        Autor autor = new Autor();
                        autor.setNome(dto.name());

                        // Conversão Integer -> LocalDate
                        if (dto.birthYear() != null) {
                            autor.setNasc(LocalDate.of(dto.birthYear(), 1, 1));
                        } else {
                            autor.setNasc(null);
                        }

                        if (dto.deathYear() != null) {
                            autor.setMorte(LocalDate.of(dto.deathYear(), 1, 1));
                        } else {
                            autor.setMorte(null);
                        }

                        return autor;
                    })
                    .collect(Collectors.toList());
        } else {
            autores = autorRepository.findAll();
        }

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado.");
            return;
        }

        autores.forEach(a -> System.out.println(
                "Nome: " + a.getNome() +
                        " | Nascimento: " + (a.getNasc() != null ? a.getNasc() : "Desconhecido") +
                        " | Morte: " + (a.getMorte() != null ? a.getMorte() : "Vivo")
        ));
    }
}