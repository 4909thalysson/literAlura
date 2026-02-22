package br.com.challenge.literalura.service;

import br.com.challenge.literalura.model.Autor;
import br.com.challenge.literalura.model.Livros;
import br.com.challenge.literalura.repository.AutorRepository;
import br.com.challenge.literalura.repository.LivrosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstatisticaService {

    private final LivrosRepository livrosRepository;
    private final AutorRepository autorRepository;

    public EstatisticaService(LivrosRepository livrosRepository,
                              AutorRepository autorRepository){
        this.livrosRepository = livrosRepository;
        this.autorRepository = autorRepository;
    }

    public List<Autor> autoresVivosNoAno(Integer ano){
        return autorRepository.autoresVivosNoAno(ano);
    }

    public List<Object[]> quantidadeLivrosPorIdioma(){
        return livrosRepository.quantidadeLivrosPorIdioma();
    }

    public List<Livros>top5LivrosMaisBaixados(){
        return livrosRepository.findTop5ByOrderByNumeroDownloadsDesc();
    }
}